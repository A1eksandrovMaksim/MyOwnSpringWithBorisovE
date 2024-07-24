package com.mycompany.myownspringwithborisove.Infrastructure;

import java.util.List;
import lombok.SneakyThrows;
import com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurator;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;



public class ObjectFactory {
    
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    
    
    @SneakyThrows
    public ObjectFactory(ApplicationContext context){
        this.context = context;
        
        var configClasses = context.getConfig()
                            .getScanner()
                            .getSubTypesOf(ObjectConfigurator.class);
        for(var configClass : configClasses){
            configurators.add(configClass.getDeclaredConstructor().newInstance());
        }
    }
    
    @SneakyThrows
    public <T> T createObject(Class<T> implClass){
        
        T t = create(implClass);
        configure(t);
        invokeInit(t);
        
        return t;
    }

    private <T> void invokeInit(T t) throws SecurityException, InvocationTargetException, IllegalAccessException {
        for(var method : t.getClass().getMethods()){
            if(method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        for(var configurator : configurators){
            configurator.configure(t, context);
        }
    }
    
    @SneakyThrows
    private <T> T create(Class<T> implClass){
        return implClass.getDeclaredConstructor().newInstance();
    }
    
    
    
    
    
}

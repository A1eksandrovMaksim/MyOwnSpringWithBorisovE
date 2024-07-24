package com.mycompany.myownspringwithborisove.Infrastructure;

import java.util.List;
import lombok.SneakyThrows;
import com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurator;
import java.util.ArrayList;



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
        
        T t = implClass.getDeclaredConstructor().newInstance();
        
        for(var configurator : configurators){
            configurator.configure(t, context);
        }
        
        return t;
    }
    
}

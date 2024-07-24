package com.mycompany.myownspringwithborisove.Infrastructure;

import java.util.List;
import lombok.SneakyThrows;
import com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurators.ObjectConfigurator;
import com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ProxyConfigurator.ProxyConfigurator;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;



public class ObjectFactory {
    
    private final ApplicationContext context;
    private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();
    
    
    @SneakyThrows
    public ObjectFactory(ApplicationContext context){
        this.context = context;
        
        
        for(var configClass : context.getConfig()
                                    .getScanner()
                                    .getSubTypesOf(ObjectConfigurator.class))
        {
            objectConfigurators.add(configClass.getDeclaredConstructor().newInstance());
        }
        
        for(var configClass : context.getConfig()
                                    .getScanner()
                                    .getSubTypesOf(ProxyConfigurator.class))
        {
            proxyConfigurators.add(configClass.getDeclaredConstructor().newInstance());
        }
    }
    
    @SneakyThrows
    public <T> T createObject(Class<T> implClass){
        
        T t = create(implClass);
        configure(t);
        invokeInit(t);
        t = wrapWithProxy(t, implClass);
        
        return t;
    }

    private <T> T wrapWithProxy(T t, Class<T> implClass) {
        for(var configurator : proxyConfigurators){
            t = (T) configurator.replaceWithProxy(t, implClass);
        }
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
        for(var configurator : objectConfigurators){
            configurator.configure(t, context);
        }
    }
    
    @SneakyThrows
    private <T> T create(Class<T> implClass){
        return implClass.getDeclaredConstructor().newInstance();
    }
    
    
    
    
    
}

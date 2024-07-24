package com.mycompany.myownspringwithborisove.Infrastructure;

import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.Singleton;
import com.mycompany.myownspringwithborisove.Infrastructure.Config.Config;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ApplicationContext {
    
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }
    
    public <T> T getObject(Class<T> type){
        if(cache.containsKey(type)){
            return (T) cache.get(type);
        }
        
        Class<? extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);
        
        if(implClass.isAnnotationPresent(Singleton.class)){
            cache.put(implClass, t);
        }
        
        return t;
    }
    
}

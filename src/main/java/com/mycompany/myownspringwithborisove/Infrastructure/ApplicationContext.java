package com.mycompany.myownspringwithborisove.Infrastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ApplicationContext {
    
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    
    public <T> T getObject(Class<T> type){
        return null;
    }
    
}

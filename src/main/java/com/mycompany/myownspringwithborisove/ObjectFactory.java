package com.mycompany.myownspringwithborisove;

import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;



public class ObjectFactory {
    
    private static ObjectFactory instance = new ObjectFactory();
      
    private Config config;
            
    public static ObjectFactory getInstance(){
        return instance;
    }
    
    private ObjectFactory(){
        config = new JavaConfig("com.mycompany.myownspringwithborisove",
                new HashMap(Map.of(Policeman.class, PolicemanImpl.class)));
    }
    
    @SneakyThrows
    public <T> T createObject(Class<T> type){
        Class<? extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
        
        // todo
        
        return t;
    }
    
}

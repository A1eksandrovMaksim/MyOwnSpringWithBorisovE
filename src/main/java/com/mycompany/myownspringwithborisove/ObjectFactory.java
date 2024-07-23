package com.mycompany.myownspringwithborisove;

import lombok.SneakyThrows;



public class ObjectFactory {
    
    private static ObjectFactory instance = new ObjectFactory();
      
    private Config config = 
            new JavaConfig("com.mycompany.myownspringwithborisove");
            
    public static ObjectFactory getInstance(){
        return instance;
    }
    
    private ObjectFactory(){
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

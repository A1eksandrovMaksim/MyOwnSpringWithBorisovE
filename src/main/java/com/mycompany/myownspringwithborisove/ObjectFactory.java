package com.mycompany.myownspringwithborisove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import com.mycompany.myownspringwithborisove.ObjectConfigurator;
import java.util.ArrayList;
import java.util.stream.Collectors;



public class ObjectFactory {
    
    private static ObjectFactory instance = new ObjectFactory();
        
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    
    public static ObjectFactory getInstance(){
        return instance;
    }
    
    @SneakyThrows
    private ObjectFactory(){
        config = new JavaConfig("com.mycompany.myownspringwithborisove",
                new HashMap(Map.of(Policeman.class, PolicemanImpl.class)));
        for(var configClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)){
            configurators.add(configClass.getDeclaredConstructor().newInstance());
        }
    }
    
    @SneakyThrows
    public <T> T createObject(Class<T> type){
        Class<? extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();
        
        for(var configurator : configurators){
            configurator.configure(t);
        }
        
        return t;
    }
    
}

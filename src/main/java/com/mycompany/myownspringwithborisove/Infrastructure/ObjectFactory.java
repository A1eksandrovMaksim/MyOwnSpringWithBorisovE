package com.mycompany.myownspringwithborisove.Infrastructure;

import com.mycompany.myownspringwithborisove.BusinessLogic.Policeman.PolicemanImpl;
import com.mycompany.myownspringwithborisove.BusinessLogic.Policeman.Policeman;
import com.mycompany.myownspringwithborisove.Infrastructure.Config.Config;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurator;
import com.mycompany.myownspringwithborisove.Infrastructure.Config.JavaConfig;
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
        
        T t = type.getDeclaredConstructor().newInstance();
        
        for(var configurator : configurators){
            configurator.configure(t);
        }
        
        return t;
    }
    
}

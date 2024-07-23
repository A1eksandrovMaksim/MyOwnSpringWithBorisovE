package com.mycompany.myownspringwithborisove;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        
        for(var field : implClass.getDeclaredFields()){
            InjectProperty annotation = 
                    field.getAnnotation(InjectProperty.class);
            
            String path = ClassLoader.getSystemClassLoader()
                            .getResource("application.yml")
                            .getPath();
            Stream<String> lines = 
                    new BufferedReader(
                            new FileReader(path)).lines();
            Map<String, String> propertiesMap = 
                    lines.map(line->line.split(": "))
                    .collect(Collectors.toMap(arr->arr[0], arr->arr[1]));
            
            
            if(annotation != null){
                String value;
                if(annotation.value().isEmpty()){
                    value = propertiesMap.get(field.getName());
                }else{
                    value = propertiesMap.get(annotation.value());
                }
                field.setAccessible(true);
                field.set(t, value);
            }
        }
        
        return t;
    }
    
}

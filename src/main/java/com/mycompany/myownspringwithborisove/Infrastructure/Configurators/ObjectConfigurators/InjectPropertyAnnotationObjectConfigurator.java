package com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurators;

import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.InjectProperty;
import com.mycompany.myownspringwithborisove.Infrastructure.ApplicationContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;



public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        String path = ClassLoader.getSystemClassLoader()
                            .getResource("application.yml")
                            .getPath();
        Stream<String> lines = 
                new BufferedReader(
                        new FileReader(path)).lines();
        propertiesMap = 
                    lines.map(line->line.split(": "))
                    .collect(Collectors.toMap(arr->arr[0], arr->arr[1]));
    }
    
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        
        Class<?> implClass = t.getClass();
        
        for(var field : implClass.getDeclaredFields()){
            InjectProperty annotation = 
                    field.getAnnotation(InjectProperty.class);
            
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
    }
}

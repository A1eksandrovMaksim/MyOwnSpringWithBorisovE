package com.mycompany.myownspringwithborisove;

import lombok.SneakyThrows;



public class InjectByTypeObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t) {
        for(var field : t.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = ObjectFactory.getInstance().createObject(field.getType());
                field.set(t, object);
            }
        }
    } 
}

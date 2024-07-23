package com.mycompany.myownspringwithborisove;

import java.util.Set;
import org.reflections.Reflections;



public class JavaConfig implements Config{

    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }
    
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> infc) {
        Set<Class<? extends T>> classes =
                scanner.getSubTypesOf(infc);
        if(classes.size() != 1){
            throw new RuntimeException(infc + " has 0 or more then one impl");
        }
        return classes.iterator().next();
    }
    
}

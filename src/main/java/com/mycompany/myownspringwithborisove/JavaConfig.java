package com.mycompany.myownspringwithborisove;

import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.reflections.Reflections;



public class JavaConfig implements Config{

    @Getter
    private Reflections scanner;
    private Map<Class,Class> infc2ImplClass;

    public JavaConfig(String packageToScan,
            Map<Class,Class> infc2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.infc2ImplClass = infc2ImplClass;
    }
    
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> infc) {
        return infc2ImplClass.computeIfAbsent(infc, aClass -> {
            Set<Class<? extends T>> classes
                    = scanner.getSubTypesOf(infc);
            if (classes.size() != 1) {
                throw new RuntimeException(infc + " has 0 or more then one impl");
            }
            return classes.iterator().next();
        });
    }
    
}

package com.mycompany.myownspringwithborisove;

import org.reflections.Reflections;

/**
 * @author Maksim
 */
public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> infc);
    
    Reflections getScanner();
    
}

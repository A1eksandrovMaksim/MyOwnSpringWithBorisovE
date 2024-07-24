package com.mycompany.myownspringwithborisove.Infrastructure.Config;

import org.reflections.Reflections;

/**
 * @author Maksim
 */
public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> infc);
    
    Reflections getScanner();
    
}

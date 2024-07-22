package com.mycompany.myownspringwithborisove;

/**
 * @author Maksim
 */
public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> infc);
    
}

package com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ProxyConfigurator;

/**
 * @author Maksim
 */
public interface ProxyConfigurator {

    Object replaceWithProxy(Object t, Class implClass);
    
}

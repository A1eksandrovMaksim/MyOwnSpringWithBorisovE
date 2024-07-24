package com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ObjectConfigurators;

import com.mycompany.myownspringwithborisove.Infrastructure.ApplicationContext;

/**
 * @author Maksim
 */
public interface ObjectConfigurator {

    void configure(Object t, ApplicationContext context);
    
}

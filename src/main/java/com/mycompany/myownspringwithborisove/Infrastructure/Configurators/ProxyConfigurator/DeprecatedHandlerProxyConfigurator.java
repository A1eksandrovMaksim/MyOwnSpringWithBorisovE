package com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ProxyConfigurator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object t, Class implClass) {
        
        if(implClass.isAnnotationPresent(Deprecated.class)){
            return Proxy.newProxyInstance(implClass.getClassLoader(),
                implClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("********* Deprecated Method **********");
                        return method.invoke(t);
                    }
                });
        }else{
            return t;
        }
        
    }
}

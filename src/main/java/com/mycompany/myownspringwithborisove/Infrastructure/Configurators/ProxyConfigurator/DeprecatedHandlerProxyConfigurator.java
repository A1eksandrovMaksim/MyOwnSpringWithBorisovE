package com.mycompany.myownspringwithborisove.Infrastructure.Configurators.ProxyConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;



public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object t, Class implClass) {
        
        if(implClass.isAnnotationPresent(Deprecated.class)){
            
            if(implClass.getInterfaces().length == 0){
                return Enhancer.create(implClass, 
                        (InvocationHandler) (Object proxy, Method method, Object[] args) -> {
                            return getInvocationHandlerLogic(method, t, args);
                        }); 
            }
            
            return Proxy.newProxyInstance(implClass.getClassLoader(),
                    implClass.getInterfaces(),
                    (Object proxy, Method method, Object[] args) ->{
                        return getInvocationHandlerLogic(method, t, args);
                    });
        }else{
            return t;
        }
        
    }

    private Object getInvocationHandlerLogic(Method method, Object t, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("********* Deprecated Method **********");
        return method.invoke(t, args);
    }
    
}

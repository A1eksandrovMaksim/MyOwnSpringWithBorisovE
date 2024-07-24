package com.mycompany.myownspringwithborisove.Infrastructure;

import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.Singleton;
import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.Singleton.LoadingPolicy;
import com.mycompany.myownspringwithborisove.Infrastructure.Config.JavaConfig;
import java.util.Map;
import org.reflections.Reflections;



public class Application {
    
    public static ApplicationContext run(String packageToScan, Map<Class, Class> infc2ImplClass){
        JavaConfig config = new JavaConfig(packageToScan, infc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        
        
        Reflections scanner = new Reflections(packageToScan);
        for(Class<?> singletonClass : scanner.getTypesAnnotatedWith(Singleton.class)){
            LoadingPolicy policy = singletonClass.getAnnotation(Singleton.class).loadingPolicy();
            if(policy != LoadingPolicy.LAZY){
                context.getObject(singletonClass);
            }
        }
        
        return context;
    }
    
}

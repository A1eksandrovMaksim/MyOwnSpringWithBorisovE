package com.mycompany.myownspringwithborisove.BusinessLogic.Recommendator;

import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.InjectProperty;
import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.Singleton;


@Singleton
@Deprecated
public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String pill;
    
    @Override
    public void recommend() {
        System.out.println("Для защиты от COVID-19, пей "+pill);
    }
}

package com.mycompany.myownspringwithborisove;



public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String pill;
    
    @Override
    public void recommend() {
        System.out.println("Для защиты от COVID-19, пей "+pill);
    }
}

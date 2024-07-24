package com.mycompany.myownspringwithborisove.BusinessLogic.Announcer;

import com.mycompany.myownspringwithborisove.BusinessLogic.Recommendator.Recommendator;
import com.mycompany.myownspringwithborisove.Infrastructure.Annotations.InjectByType;



public class ConsoleAnouncer implements Announcer {

    @InjectByType
    private Recommendator recommendator;
    
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}

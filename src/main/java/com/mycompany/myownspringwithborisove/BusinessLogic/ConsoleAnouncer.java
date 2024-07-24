package com.mycompany.myownspringwithborisove.BusinessLogic;

import com.mycompany.myownspringwithborisove.Infrastructure.ObjectFactory;
import com.mycompany.myownspringwithborisove.BusinessLogic.Recommendator.Recommendator;
import com.mycompany.myownspringwithborisove.BusinessLogic.Announcer.Announcer;



public class ConsoleAnouncer implements Announcer {

    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);
    
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}

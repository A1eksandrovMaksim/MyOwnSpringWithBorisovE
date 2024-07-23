package com.mycompany.myownspringwithborisove;



public class ConsoleAnouncer implements Announcer {

    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);
    
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}

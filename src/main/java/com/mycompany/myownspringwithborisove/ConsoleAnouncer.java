package com.mycompany.myownspringwithborisove;



public class ConsoleAnouncer implements Announcer {

    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}

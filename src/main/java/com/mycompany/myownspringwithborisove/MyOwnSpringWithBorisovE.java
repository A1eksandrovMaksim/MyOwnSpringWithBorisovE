package com.mycompany.myownspringwithborisove;

public class MyOwnSpringWithBorisovE {

    public static void main(String[] args) {
        var desinfector = 
                ObjectFactory.getInstance()
                .createObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}

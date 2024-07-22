package com.mycompany.myownspringwithborisove;

public class MyOwnSpringWithBorisovE {

    public static void main(String[] args) {
        var desinfector = new CoronaDesinfector();
        desinfector.start(new Room());
    }
}

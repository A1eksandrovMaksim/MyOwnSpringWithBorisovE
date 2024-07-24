package com.mycompany.myownspringwithborisove;

import com.mycompany.myownspringwithborisove.Infrastructure.ObjectFactory;
import com.mycompany.myownspringwithborisove.BusinessLogic.Room;
import com.mycompany.myownspringwithborisove.BusinessLogic.CoronaDesinfector;

public class MyOwnSpringWithBorisovE {

    public static void main(String[] args) {
        var desinfector = 
                ObjectFactory.getInstance()
                .createObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}

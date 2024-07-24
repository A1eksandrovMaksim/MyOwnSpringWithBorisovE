package com.mycompany.myownspringwithborisove;

import com.mycompany.myownspringwithborisove.BusinessLogic.CoronaDesinfector;
import com.mycompany.myownspringwithborisove.BusinessLogic.Policeman.Policeman;
import com.mycompany.myownspringwithborisove.BusinessLogic.Policeman.PolicemanImpl;
import com.mycompany.myownspringwithborisove.BusinessLogic.Room;
import com.mycompany.myownspringwithborisove.Infrastructure.Application;
import com.mycompany.myownspringwithborisove.Infrastructure.ApplicationContext;
import java.util.HashMap;
import java.util.Map;

public class MyOwnSpringWithBorisovE {

    public static void main(String[] args) {
        ApplicationContext context = 
                Application.run("com.mycompany.myownspringwithborisove", 
                        new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
        
        CoronaDesinfector coronaDesinfector = 
                context.getObject(CoronaDesinfector.class);
        coronaDesinfector.start(new Room());
        
        
    }
}

package com.mycompany.myownspringwithborisove;



public class AngryPoliceman implements Policeman {

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Те, кто не вышел, ПЛАТИТЕ ШТРАФ, и выходите");
    } 
}

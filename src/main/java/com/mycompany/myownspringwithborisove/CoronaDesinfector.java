package com.mycompany.myownspringwithborisove;



public class CoronaDesinfector {
    
    private Announcer announcer = new ConsoleAnouncer();
    
    private Policeman policeman = new PolicemanImpl();
    
    public void start(Room room){
        announcer.announce("Всем выйти! Начинается дезинфекия.");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Дезинфекция завершена. Комната доступна");
    }
    
    public void desinfect(Room room){
        System.out.println("Проводится дезинфекция ! ! !");
    }
    
}

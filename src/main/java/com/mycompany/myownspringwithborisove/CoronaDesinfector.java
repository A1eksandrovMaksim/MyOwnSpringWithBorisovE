package com.mycompany.myownspringwithborisove;



public class CoronaDesinfector {
    
    @InjectByType
    private Announcer announcer;
    
    @InjectByType
    private Policeman policeman;
    
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

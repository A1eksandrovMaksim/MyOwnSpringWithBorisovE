package com.mycompany.myownspringwithborisove;



public class CoronaDesinfector {
    
    public void start(Room room){
        // todo Сообщить всем присутствующим в комнате, 
        //      о начале дещинфекции, и попросить всех уйти.
        // todo принудительно выгнать тех, кто сам не ушел.
        desinfect(room);
        // todo сообщить всем присутствовашим в комнате, что
        //      они могут вернуться обратно
    }
    
    public void desinfect(Room room){
        System.out.println("Проводится дезинфекция ! ! !");
    }
    
}

package org.groupTw;


import java.awt.*;
/*
Klasa fabryki, zwraca ona każdą możliwą nową instancję jednostki, ułatwia tworzenie jednostek, armi etc.
 */
public class EntityFactory {

    public Entity addEntity(String type) throws NullPointerException {
        if(type.equalsIgnoreCase("Warrior"))
            return new Warrior();
        if(type.equalsIgnoreCase("Archer"))
            return new Archer();
        //...
        throw new NullPointerException("No such a Unit");
    }

    public Entity addEntity(String type, Point position_) throws NullPointerException {
        if(type.equalsIgnoreCase("Warrior"))
            return new Warrior(position_);
        if(type.equalsIgnoreCase("Archer"))
            return new Archer();
        //...
        throw new NullPointerException("No such a Unit");
    }
}

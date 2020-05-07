package org.groupTw;


import java.awt.*;

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
            return new Archer(position_);
        //...
        throw new NullPointerException("No such a Unit");
    }

}
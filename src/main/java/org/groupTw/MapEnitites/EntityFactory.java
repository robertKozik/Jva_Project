package org.groupTw.MapEnitites;


import java.awt.*;

public class EntityFactory {

    public Entity addEntity(String type) throws NullPointerException {
        if(type.equalsIgnoreCase("Warrior"))
            return new Warrior();
        else if(type.equalsIgnoreCase("Archer"))
            return new Archer();
        //...
        throw new NullPointerException("No such a Unit");
    }

    public Entity addEntity(String type_, Point position_) throws NullPointerException {
        String type = type_.toUpperCase();
        switch (type){
            case "WARRIOR":
                return new Warrior(position_);
            case "ARCHER":
                return new Archer(position_);

            case "ARCHER TOWER":
                return new ArcherTower(position_);
        }
        throw new NullPointerException("No such a Unit");
    }

}

package org.groupTw.MapEnitites;


import java.awt.*;
// factory invokes builder ?
public class EntityFactory {

    public Entity addEntity(String type_) throws NullPointerException {
        String type = type_.toUpperCase();
        switch (type){
            case "WARRIOR":
                return new Warrior();
            case "ARCHER":
                return new Archer();

            case "ARCHER TOWER":
                return new ArcherTower();
        }
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

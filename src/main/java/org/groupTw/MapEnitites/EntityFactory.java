package org.groupTw.MapEnitites;


import java.awt.*;
// factory invokes builder ?
public class EntityFactory {

    public Entity addEntity(String type_, String color_) throws NullPointerException {
        String type = type_.toUpperCase();
        switch (type){
            case "WARRIOR":
                return new Warrior(color_);
            case "ARCHER":
                return new Archer(color_);
            case "ARCHER TOWER":
                return new ArcherTower(color_);
            case "SIEGE":
                return new Siege(color_);
            case "BARRACKS":
                return new Barracks(color_);
            case "MERCENARY":
                return new Mercenary(color_);
        }
        throw new NullPointerException("No such a Unit");
    }

    public Entity addEntity(String type_, Point position_, String color_) throws NullPointerException {
        String type = type_.toUpperCase();
        switch (type){
            case "WARRIOR":
                return new Warrior(position_,color_);
            case "ARCHER":
                return new Archer(position_,color_);
            case "ARCHER TOWER":
                return new ArcherTower(position_,color_);
            case "SIEGE":
                return new Siege(position_,color_);
            case "BARRACKS":
                return new Barracks(position_,color_);
            case "MERCENARY":
                return new Mercenary(position_,color_);
        }
        throw new NullPointerException("No such a Unit");
    }

}

package org.groupTw.MapEnitites;


import java.awt.*;
public class EntityFactory {

    public Entity addEntity(UnitEnum type_, UnitEnum color_) throws NullPointerException {

        switch (type_) {
            case WARRIOR:
                return new Warrior(color_);
            case ARCHER:
                return new Archer(color_);
            case CATAPULT:
                return new Catapult(color_);
            case SIEGE:
                return new Siege(color_);
            case TOWER:
                return new Tower(color_);
            case MERCENARY:
                return new Mercenary(color_);
        }
        throw new NullPointerException("No such a Unit");
    }

    public Entity addEntity(UnitEnum type_, Point position_, UnitEnum color_) throws NullPointerException {

        switch (type_) {
            case WARRIOR:
                return new Warrior(position_, color_);
            case ARCHER:
                return new Archer(position_, color_);
            case CATAPULT:
                return new Catapult(position_, color_);
            case SIEGE:
                return new Siege(position_, color_);
            case TOWER:
                return new Tower(position_, color_);
            case MERCENARY:
                return new Mercenary(position_, color_);
        }
        throw new NullPointerException("No such a Unit");
    }

}

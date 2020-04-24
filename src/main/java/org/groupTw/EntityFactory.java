package org.groupTw;


public class EntityFactory {

    public Entity addEntity(String type) throws NullPointerException {
        if(type.equalsIgnoreCase("Warrior"))
            return new Warrior();
        if(type.equalsIgnoreCase("Archer"))
            return new Archer();
        //...
        throw new NullPointerException("No such a Unit");
    }
}

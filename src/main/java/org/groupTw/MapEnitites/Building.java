package org.groupTw.MapEnitites;

import java.awt.*;

public abstract class Building extends Entity {

    public Building(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_){
        super(position_, imagePath_, health_, attack_, defense_, canAttack_);
        AttackPattern();
    }


    abstract void AttackPattern();
}

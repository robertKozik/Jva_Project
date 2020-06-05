package org.groupTw.MapEnitites;

import java.awt.*;

abstract class Building extends Entity {

    public Building(String imgPath_) {
        super(imgPath_);
        AttackPattern();
    }

    public Building(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_){
        super(position_, imagePath_, health_, attack_, defense_, canAttack_);
        AttackPattern();
    }


    abstract void AttackPattern();
}

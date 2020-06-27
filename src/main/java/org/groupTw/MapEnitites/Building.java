package org.groupTw.MapEnitites;

import java.awt.*;

abstract public class Building extends Entity {

    public Building(String imgPath_, int health_, int attack_, int defense_) {
        super(imgPath_,health_,attack_,defense_);
        AttackPattern();
    }

    public Building(Point position_, String imagePath_, int health_, int attack_, int defense_){
        super(position_, imagePath_, health_, attack_, defense_);
        AttackPattern();
    }

    public void evaluatePatterns(){
        AttackPattern();
    }
    abstract void AttackPattern();
}

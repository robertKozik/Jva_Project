package org.groupTw.MapEnitites;

import java.awt.*;

public class Base extends Building {


    public Base(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_, String name_) {
        super(position_, "src/Art/BarracksBlue.png", health_, attack_, defense_, canAttack_, name_);
    }//constructor

    public Base(Point position_){
        super(position_, "src/Art/BarracksBlue.png", 200, 0, 50, false, "Base");
    }//constructor

    void AttackPattern(){}
}

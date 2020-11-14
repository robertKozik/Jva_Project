package org.groupTw.MapEnitites;

import org.groupTw.AppFrame;

import java.awt.*;

class Catapult extends Building {

    public Catapult(ColorEnum color_) {
        super("/catapult" + color_.getValue() + ".png", 100, 10);
        setColor(color_);
    }

    public Catapult(Point position_, ColorEnum color_) {
        super(position_, "/catapult" + color_.getValue() + ".png", 100, 10);
        setColor(color_);
    }//constructor

    /*
        X X X X X
        X X X X X
        X X 0 X X
        X X X X X
        X X X X X
         */
    @Override
    void AttackPattern(){
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for (int x_ = -2; x_ < 3; x_++) {
            int attackX = x + x_;
            for (int y_ = -2; y_ < 3; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int attackY = y + y_;
                if (attackX > -1 && attackX < AppFrame.MAPSIZE && attackY > -1 && attackY < AppFrame.MAPSIZE)
                    this.getPossible_attacks().add(new Point(attackX, attackY));
            }
        }
    }

    @Override
    public String toString() {
        return "Catapult"+getColor();
    }
}

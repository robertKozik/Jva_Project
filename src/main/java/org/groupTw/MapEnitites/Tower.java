package org.groupTw.MapEnitites;

import org.groupTw.AppFrame;

import java.awt.*;

class Tower extends Building {

    public Tower(String color_) {
        super("src/Art/tower"+color_.toLowerCase()+".png");
        setColor(color_);
    }

    public Tower(Point position_, String color_, int health_, int attack_, int defense_, boolean canAttack_) {
        super(position_, "src/Art/tower"+color_.toLowerCase()+".png", health_, attack_, defense_, canAttack_);
        setColor(color_);
    }//constructor

    public Tower(Point position_, String color_){
        super(position_, "src/Art/tower"+color_.toLowerCase()+".png", 40, 5, 20, true);
        setColor(color_);
    }//constructor

    /*
    Attack pattern :
    - - - - -
    - X X X -
    - X 0 X -
    - X X X -
    - - - - -
     */
    @Override
    void AttackPattern() {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for (int x_ = -1; x_ < 2; x_++) {
            int attackX = x + x_;
            for (int y_ = -1; y_ < 2; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int attackY = y + y_;
                if (attackX > -1 && attackX < AppFrame.MAPSIZE && attackY > -1 && attackY < AppFrame.MAPSIZE) this.getPossible_attacks().add(new Point(attackX, attackY));
            }
        }

    }

    @Override
    public String toString() {
        return "barracks"+getColor();
    }
}


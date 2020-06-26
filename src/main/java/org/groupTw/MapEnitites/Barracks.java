package org.groupTw.MapEnitites;

import java.awt.*;

class Barracks extends Building {

    public Barracks(String color_) {
        super("src/Art/barracks"+color_.toLowerCase()+".png");
    }

    public Barracks(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_) {
        super(position_, "src/Art/barracks.png", health_, attack_, defense_, canAttack_);
    }//constructor

    public Barracks(Point position_, String color_){
        super(position_, "src/Art/barracks"+color_.toLowerCase()+".png", 40, 5, 20, true);
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
                if (attackX > -1 && attackX < 8 && attackY > -1 && attackY < 8) this.getPossible_attacks().add(new Point(attackX, attackY));
            }
        }

    }

    @Override
    public String toString() {
        return "barracks";
    }
}


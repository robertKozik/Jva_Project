package org.groupTw.MapEnitites;

import java.awt.*;

class ArcherTower extends Building {

    public ArcherTower(String color_) {
        super("src/Art/tower"+color_.toLowerCase()+".png");
    }

    public ArcherTower(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_) {
        super(position_, "src/Art/barracks.png", health_, attack_, defense_, canAttack_);
    }//constructor

    public ArcherTower(Point position_, String color_){
        super(position_, "src/Art/tower"+color_.toLowerCase()+".png", 100, 10, 50, true);
    }//constructor

    /*
        X X X X X
        X X X X X
        X X 0 X X
        X X X X X
        X X X X X
         */
    void AttackPattern(){
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for (int x_ = -2; x_ < 3; x_++) {
            int attackX = x + x_;
            for (int y_ = -2; y_ < 3; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int attackY = y + y_;
                if (attackX > -1 && attackX < 8 && attackY > -1 && attackY < 8) this.getPossible_attacks().add(new Point(attackX, attackY));
            }
        }
    }

    @Override
    public String toString() {
        return "archer tower";
    }
}

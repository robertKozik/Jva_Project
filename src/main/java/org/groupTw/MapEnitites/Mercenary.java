package org.groupTw.MapEnitites;

import java.awt.*;

class Mercenary extends MovingUnit {

    public Mercenary(String color_){
        super("src/Art/mercenary"+color_+".png");
        setColor(color_);
        MovePattern();
        AttackPattern();
    }//constructor

    public Mercenary(Point position_, String color_){
        super(position_,"src/Art/mercenary"+color_+".png",10,10,10,true);
        MovePattern();
        AttackPattern();
    } //constructor

    /*
    attack pattern:
    - - - - -
    - - - - -
    - - - - -
    - X X X -
    - X 0 X -
    - X X X -
    - - - - -
    - - - - -
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
    /*
    move pattern
    - - X - -
    - X X X -
    X X 0 X X
    - X X X -
    - - X - -
     */
    @Override
    public void MovePattern() {

        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_moves().clear();

        for (int x_ = -1; x_ < 2; x_++) {
            int moveX = x + x_;
            for (int y_ = -1; y_ < 2; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int moveY = y + y_;
                if (moveX > -1 && moveX < 8 && moveY > -1 && moveY < 8) this.getPossible_moves().add(new Point(moveX, moveY));
            }
        }

        for (int x_ = -2; x_ < 3; x_+=2) {
            int moveX = x + x_;
            for (int y_ = -2; y_ < 3; y_ += 2) {
                int moveY = y + y_;
                if(y_ == x_ || y_ == -x_)continue;
                if (moveX > -1 && moveX < 8 && moveY > -1 && moveY < 8) this.getPossible_moves().add(new Point(moveX, moveY));
            }
        }
    }

    @Override
    public String toString() {
        return "mercenary";
    }
}



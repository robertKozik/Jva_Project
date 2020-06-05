package org.groupTw.MapEnitites;

import org.groupTw.iMovable;

import java.awt.*;

class Archer extends MovingUnit {

    public Archer(){
        super("src/Art/dagger.png");
        MovePattern();
        AttackPattern();
    }//constructor


    public Archer(Point position_, int health_, int attack_, int defense_, boolean canAttack_) {
        super(position_, "src/Art/dagger.png", health_, attack_, defense_, canAttack_);
        MovePattern();
        AttackPattern();

    } //constructor

    public Archer(Point position_){
        super(position_,"src/Art/dagger.png",10,10,10,true);
        MovePattern();
        AttackPattern();
    } //constructor

    /*
    attack pattern:
    - - - - -
    - - X - -
    - - X - -
    - - X - -
    - - 0 - -
    - - X - -
    - - X - -
    - - X - -
    - - - - -
     */
    @Override
    void AttackPattern() {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for( int x_ = -3; x_< 4; x_++) {
            if(x_ == 0)continue;
            this.getPossible_attacks().add(new Point( x+x_,y ));
        }

    }
    /*
    move pattern
    - - - - -
    - X X X -
    - X 0 X -
    - X X X -
    - - - - -
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
    }

    @Override
    public String toString() {
        return "archer";
    }
}

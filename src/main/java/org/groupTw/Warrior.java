package org.groupTw;

import java.awt.*;


public class Warrior extends MovingUnit implements iMovable {


    //Constructor
    public Warrior(){
        super();
        MovePattern();
        AttackPattern();
    }

    public Warrior(Point position_, int health_, int attack_, int defense_, boolean canAttack_) {
        super(position_, "src/Art/Warrior.png", health_, attack_, defense_, canAttack_);
        MovePattern();
        AttackPattern();

    }

    public Warrior (Point position_){
        super(position_,"src/Art/Warrior.png",10,10,10,true);
        AttackPattern();
        MovePattern();
    }


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


    /*
    move pattern :
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


}

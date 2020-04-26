package org.groupTw;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Warrior extends Entity implements iMovable {

    private ArrayList<Point> possible_moves;

    public Warrior(){
        super();
        MovePattern();
        AttackPattern();
    }
    //Constructor
    public Warrior(Point position_, int health_, int attack_, int defense_, boolean canAttack_, boolean can_Move) {
        super(position_, "src/Art/paladin.png", health_, attack_, defense_, canAttack_);
        MovePattern();
        AttackPattern();

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
        int x = this.position.x;
        int y = this.position.y;
        this.possible_attacks = new ArrayList<>();
        for (int x_ = -1; x_ < 1; x_++) {
            int attackX = x + x_;
            for (int y_ = -1; y_ < 1; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int attackY = y + y_;
                if (attackX > -1 && attackX < 8 && attackY > -1 && attackY < 8) this.possible_moves.add(new Point(attackX, attackY));
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

        int x = this.position.x;
        int y = this.position.y;
        this.possible_moves = new ArrayList<>();

        for (int x_ = -1; x_ < 1; x_++) {
             int moveX = x + x_;
            for (int y_ = -1; y_ < 1; y_++) {
                if(x_ == 0 && y_== 0) continue;
                int moveY = y + y_;
                if (moveX > -1 && moveX < 8 && moveY > -1 && moveY < 8) this.possible_moves.add(new Point(moveX, moveY));
            }
        }
    }

    @Override
    public void Move(Point position_) {
        this.position = position_;
    }

}

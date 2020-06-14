package org.groupTw.MapEnitites;

import java.awt.*;


public class Siege extends MovingUnit {

    public Siege(){
        super();
        MovePattern();
        AttackPattern();
    }//constructor


    public Siege(Point position_, int health_, int attack_, int defense_, boolean canAttack_, String name_) {
        super(position_, "src/Art/CatapultBlue.png", health_, attack_, defense_, canAttack_, name_);
        MovePattern();
        AttackPattern();

    } //constructor

    public Siege(Point position_){
        super(position_,"src/Art/CatapultBlue.png",10,50,5,true,"Siege");
        MovePattern();
        AttackPattern();
    } //constructor

    /*
    attack pattern:
    - - - - - - -
    X X X X X X X
    X - - - - - X
    X - - - - - X
    X - - 0 - - X
    X - - - - - X
    X - - - - - X
    X X X X X X X
    - - - - - - -
     */
    @Override
    void AttackPattern() {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for (int x_ = -3; x_ < 4; x_ += 1) {
            int attackX = x + x_;
            for (int y_ = -3; y_ < 4; y_ += 1) {
                int attackY = y + y_;
                if (x_ == -3 || x_ == 3 || y_ == -3 || y_ == 3) {
                    if (attackX > -1 && attackX < 8 && attackY > -1 && attackY < 8)
                        this.getPossible_attacks().add(new Point(attackX, attackY));
                }
            }
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

}

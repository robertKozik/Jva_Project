package org.groupTw.MapEnitites;

import org.groupTw.AppFrame;

import java.awt.*;

class Siege extends MovingUnit {

    public Siege(UnitEnum color_) {
        super("/siege" + color_.getValue() + ".png", 10, 50);
        setColor(color_);
    }//constructor

    public Siege(Point position_, UnitEnum color_) {
        super(position_, "/siege" + color_.getValue() + ".png", 10, 50);
        setColor(color_);
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
                    if (attackX > -1 && attackX < AppFrame.MAPSIZE && attackY > -1 && attackY < AppFrame.MAPSIZE)
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
                if (moveX > -1 && moveX < AppFrame.MAPSIZE && moveY > -1 && moveY < AppFrame.MAPSIZE)
                    this.getPossible_moves().add(new Point(moveX, moveY));
            }
        }
    }

    @Override
    public String toString() {
        return "siege"+getColor();
    }
}

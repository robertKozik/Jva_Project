package org.groupTw;

import java.awt.*;
import java.util.ArrayList;

public class Archer extends Entity implements iMovable {

    /*
    attack pattern:
    - - - - -
    - - X - -
    - - X - -
    - - X - -
    - - 0 - -
    - - - - -
     */
    @Override
    void AttackPattern() {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        this.getPossible_attacks().clear();
        for( int y_ = 1; y_< 4; y_++) this.getPossible_attacks().add(new Point( x,y+y_ ));

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
    public void Move(Point position_) {
        this.setPosition(position_);
    }
}

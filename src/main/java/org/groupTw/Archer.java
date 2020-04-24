package org.groupTw;

import java.awt.*;
import java.util.ArrayList;

public class Archer extends Entity implements iMovable {

    private ArrayList<Point> possible_moves;
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
        int x = this.position.x;
        int y = this.position.y;
        this.possible_attacks = new ArrayList<>();
        for( int y_ = 1; y_< 4; y_++) this.possible_attacks.add(new Point( x,y+y_ ));

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

        int x = this.position.x;
        int y = this.position.y;
        this.possible_moves = new ArrayList<>();
        for (int x_ = -1; x_ < 1; x_++) {
            for (int y_ = -1; y_ < 1; y_++) {
                if (x > -1 && x < 8 && y > -1 && y < 8) this.possible_moves.add(new Point(x, y));
            }
        }
        this.possible_moves.remove(4); //current position
    }

    @Override
    public void Move(Point position_) {

    }
}

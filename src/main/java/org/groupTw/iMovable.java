package org.groupTw;

import java.awt.*;
import java.util.ArrayList;

/**
 * interface which is needed to be implement in order to make entity which can be moved in game
 */
public interface iMovable {

    /**
     * Method calculates all possible moves that Enitity can perform
     */
    void MovePattern();

    /**
     * Method updates entity position on map
     *
     * @param position_ Point where entity will be moved
     */
    void Move(Point position_);

    /**
     * returns array
     *
     * @return array of all possible moves which entity can perform
     */
    ArrayList<Point> getPossible_moves();

}

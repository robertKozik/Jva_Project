package org.groupTw;

import java.awt.*;
import java.util.ArrayList;

public interface iMovable {

    void MovePattern();

    void Move(Point position_);

    ArrayList<Point> getPossible_moves();

}

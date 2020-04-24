package org.groupTw;

import java.awt.*;
import java.util.List;

public class Entity {
    Point position;
    //List<Point> possible_moves;
    //List<Point> possible_attack;

    int health;
    int attack;
    int defense;

    public Entity(int x, int y){
        position = new Point(x,y);
       // possible_attack = new List<Point>();
       // possible_moves = new List<> ();
        health = 10;
        attack = 10;
        defense = 10;
    }
}

package org.groupTw.MapEnitites;

import org.groupTw.iMovable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

abstract public class MovingUnit extends Entity implements iMovable {
    private ArrayList<Point> possible_moves;

    public MovingUnit(String imagePath_, int health_, int attack_, int defense_){
        super(imagePath_,health_,attack_,defense_);
        this.possible_moves = new ArrayList<>();
        evaluatePatterns();

    }

    public MovingUnit(Point position_, String imagePath_, int health_, int attack_, int defense_ ){
        super(position_, imagePath_, health_, attack_, defense_ );
        this.possible_moves = new ArrayList<>();
        evaluatePatterns();
    }

    public void evaluatePatterns(){
        MovePattern();
        AttackPattern();
    }
    abstract public void MovePattern();
    public void Move(Point position_){
        this.setPosition(position_);
        MovePattern();
        AttackPattern();
    }

    public ArrayList<Point> getPossible_moves() {
        return possible_moves;
    }

    public void setPossible_moves(ArrayList<Point> possible_moves) {
        this.possible_moves = possible_moves;
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), possible_moves);
    }
}

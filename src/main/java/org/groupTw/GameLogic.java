package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private ArrayList<MapPanel> possibleAttacks;
    private ArrayList<MapPanel> possibleMoves;
    static private int roundCounter;

    public GameLogic(){
        roundCounter = 0;
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
    }
    public void tileNotSelected(MapPanel Tile_, ArrayList<MapPanel> mapTiles_){
            setMoveBorders(Tile_, mapTiles_, Color.MAGENTA);
            setAttackBorders(Tile_, mapTiles_, Color.ORANGE);
    }
    public void tileSelected(MapPanel Tile_,MapPanel selected_, ArrayList<MapPanel> mapTiles_){
        if(this.possibleMoves.contains(Tile_) && Tile_ != selected_){
            moveEntity(Tile_,selected_);
        }
        this.possibleMoves.clear();
        this.possibleAttacks.clear();
        return;
    }

    private void setAttackBorders(MapPanel Tile_, ArrayList<MapPanel> mapTiles_, Color color_){
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleAttacks = new ArrayList<>();
        for(Point point : entityOnTile.getPossible_attacks()){
            for(MapPanel tile : mapTiles_){
                Point tilePosition = (Point)tile.getClientProperty("Position");
                if(tilePosition.equals(point) && tile.isOccupied()) {
                    tile.setBorder(color_,3);
                    this.possibleAttacks.add(tile);
                }
            }
        }
    }
    private void setMoveBorders(MapPanel Tile_, ArrayList<MapPanel> mapTiles_, Color color_) {
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleMoves = new ArrayList<>();
        for (Point point : entityOnTile.getPossible_moves()) {
            for (MapPanel tile : mapTiles_) {
                Point tilePosition = (Point) tile.getClientProperty("Position");
                if (tilePosition.equals(point) && !tile.isOccupied()) {
                    tile.setBorder(color_,3);
                    this.possibleMoves.add(tile);
                    }
            }
        }
        //System.out.println(this.possibleMoves.size());
    }

    private void moveEntity(MapPanel tile_, MapPanel selected_){
        roundCounter++;
        selected_.getEntity_on_tile().Move((Point)tile_.getClientProperty("Position"));
        selected_.removeAll();
        selected_.setEntity_on_tile(null);
        this.possibleMoves.clear();
        this.possibleAttacks.clear();

    }

    public ArrayList<MapPanel> getPossibleAttacks() {
        return possibleAttacks;
    }

    public void setPossibleAttacks(ArrayList<MapPanel> possibleAttacks) {
        this.possibleAttacks = possibleAttacks;
    }

    public ArrayList<MapPanel> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<MapPanel> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public static int getRoundCounter() {
        return roundCounter;
    }

    public void clearArrAttacks(){
        this.possibleAttacks.clear();
    }
    public void clearArrMoves(){
        this.possibleMoves.clear();
    }
}

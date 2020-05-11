package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private ArrayList<MapPanel> possibleAttacks;
    private ArrayList<MapPanel> possibleMoves;
    static private int roundCounter;

    public GameLogic(int roundCounter_){
        roundCounter = roundCounter_;
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
    }
    public GameLogic(){
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
    }
    public void tileNotSelected(MapPanel Tile_, MapPanel[][] mapTiles_){
            setMoveBorders(Tile_, mapTiles_, Color.MAGENTA);
            setAttackBorders(Tile_, mapTiles_, Color.ORANGE);
    }
    public void tileSelected(MapPanel Tile_,MapPanel selected_, MapPanel[][] mapTiles_){
        if(this.possibleMoves.contains(Tile_) && Tile_ != selected_){
            moveEntity(Tile_,selected_);
        }
        else if(this.possibleAttacks.contains(Tile_) && Tile_ != selected_){
            attackEntity(Tile_,selected_);
        }
        this.possibleMoves.clear();
        this.possibleAttacks.clear();
        return;
    }

    private void setAttackBorders(MapPanel Tile_, MapPanel[][] mapTiles_, Color color_){
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleAttacks = new ArrayList<>();
        for(Point point : entityOnTile.getPossible_attacks()){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++) {
                    Point tilePosition = (Point) mapTiles_[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point) && mapTiles_[i][j].isOccupied()  &&  mapTiles_[i][j].getOwner()!=Tile_.getOwner()) {
                        mapTiles_[i][j].setBorder(color_, 3);
                        this.possibleAttacks.add(mapTiles_[i][j]);
                    }
                }
            }
        }
    }
    private void setMoveBorders(MapPanel Tile_,  MapPanel[][] mapTiles_, Color color_) {
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleMoves = new ArrayList<>();
        for (Point point : entityOnTile.getPossible_moves()) {
            for (int i=0;i<8;i++) {
                for (int j=0;j<8;j++) {
                    Point tilePosition = (Point) mapTiles_[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point) && !mapTiles_[i][j].isOccupied()) {
                        mapTiles_[i][j].setBorder(color_,3);
                        this.possibleMoves.add(mapTiles_[i][j]);
                        }
                }
            }
        }
    }

    private void moveEntity(MapPanel tile_, MapPanel selected_){
        this.roundCounter++;
        selected_.getEntity_on_tile().Move((Point)tile_.getClientProperty("Position"));
        selected_.removeAll();
        selected_.setEntity_on_tile(null);
        this.possibleMoves.clear();
        this.possibleAttacks.clear();

    }
    private void attackEntity(MapPanel tile_, MapPanel selected_){
        this.roundCounter++;
        tile_.getEntity_on_tile().Move((Point)selected_.getClientProperty("Position"));
        tile_.removeAll();
        tile_.setEntity_on_tile(null);
        System.out.println(tile_.getEntity_on_tile());
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

package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameLogic implements iLogic{
    private ArrayList<MapPanel> possibleAttacks;
    private ArrayList<MapPanel> possibleMoves;
    private MapPanel selected;
    private Player[] playersArr;
    static private int roundCounter;
    private Player currentPlayer;

    public GameLogic(Player[] playersArr_){
        playersArr = playersArr_;
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
        currentPlayer = playersArr[roundCounter%2];
        selected = null;
    }

    public void action (MapPanel tile_, MapPanel[][] mapTiles_) {
        currentPlayer = playersArr[roundCounter%2];
        Army currentArmy = currentPlayer.getArmy();
        if (selected == null && currentArmy.getTroops().contains(tile_.getEntity_on_tile())) {
            System.out.println(1);
            tile_.setBorder(Color.RED, 2);
            tileNotSelected(tile_, mapTiles_);
            selected = tile_;
        } else if (selected != null && !(tile_.equals(selected.getClientProperty("Position")) && tile_.getEntity_on_tile() == null)) {
            System.out.println(2);
            tileSelected(tile_, mapTiles_);
            selected.setBorder(Color.BLACK, 0);
            selected = null;

        } else {
            selected = null;
            System.out.println(3);
            clearArrAttacks();
            clearArrMoves();
        }
    }

    public void tileNotSelected(MapPanel Tile_, MapPanel[][] mapTiles_){
            setMoveBorders(Tile_, mapTiles_, Color.MAGENTA);
            setAttackBorders(Tile_, mapTiles_, Color.ORANGE);
    }
    public void tileSelected(MapPanel Tile_, MapPanel[][] mapTiles_){
        if(this.possibleMoves.contains(Tile_) && Tile_ != selected){
            moveEntity(Tile_,selected);
        }
        else if(this.possibleAttacks.contains(Tile_) && Tile_ != selected){
            attackEntity(Tile_,selected);
        }
        this.clearArrAttacks();
        this.clearArrMoves();
    }

    private void setAttackBorders(MapPanel Tile_, MapPanel[][] mapTiles_, Color color_){
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleAttacks = new ArrayList<>();
        for(Point point : entityOnTile.getPossible_attacks()){
            for(int i=0;i<AppFrame.MAPSIZE;i++){
                for(int j=0;j<AppFrame.MAPSIZE;j++) {
                    Point tilePosition = (Point) mapTiles_[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point) && mapTiles_[i][j].isOccupied()  &&  mapTiles_[i][j].getOwner()!=Tile_.getOwner()) {
                        mapTiles_[i][j].setBorder(color_, 1);
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
                        mapTiles_[i][j].setBorder(color_,1);
                        this.possibleMoves.add(mapTiles_[i][j]);
                        }
                }
            }
        }
    }

    private void moveEntity(MapPanel tile_, MapPanel selected_){
        GameLogic.roundCounter++;
        Entity entity = selected_.getEntity_on_tile();
        entity.Move((Point)tile_.getClientProperty("Position"));
        tile_.setEntity_on_tile(entity);
        tile_.setOwner(selected_.getOwner());
        selected_.removeAll();
        selected_.setEntity_on_tile(null);
        selected_.setOwner(null);
        clearArrMoves();
        clearArrAttacks();

    }
    private void attackEntity(MapPanel tile_, MapPanel selected_){
        GameLogic.roundCounter++;
        tile_.getEntity_on_tile().setPosition(null);
        tile_.removeAll();
        tile_.setEntity_on_tile(null);
        tile_.setOwner(null);
        System.out.println(tile_.getEntity_on_tile());
        clearArrMoves();
        clearArrAttacks();

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
        for(MapPanel panel : possibleAttacks){
            panel.setBorder(Color.BLACK,0);
        }
        this.possibleAttacks.clear();
    }
    public void clearArrMoves(){
        for(MapPanel panel : possibleMoves){
            panel.setBorder(Color.BLACK,0);
        }
        this.possibleMoves.clear();
    }

    public MapPanel getSelected() {
        return selected;
    }

    public void setSelected(MapPanel selected) {
        this.selected = selected;
    }

    public Player[] getPlayersArr() {
        return playersArr;
    }

    public void setPlayersArr(Player[] playersArr) {
        this.playersArr = playersArr;
    }

    public static void setRoundCounter(int roundCounter) {
        GameLogic.roundCounter = roundCounter;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

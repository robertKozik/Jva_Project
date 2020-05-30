package org.groupTw;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic implements iLogic {
    static private int roundCounter; //number of current round
    private ArrayList<MapPanel> possibleAttacks; //possible attacks of current selected unit
    private ArrayList<MapPanel> possibleMoves;  //possible moves of current selected unit
    private MapPanel selected;//selected map tile
    private Player[] playersArr;
    private Player currentPlayer;//player whose can play right now
    private Entity toShow;  //entity which is requested to show statistics


    //constructor
    public GameLogic(Player[] playersArr_) {
        playersArr = playersArr_;
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
        currentPlayer = playersArr[roundCounter % 2];
        toShow = null;
        selected = null;
    }

    public static int getRoundCounter() {
        return roundCounter;
    }

    public static void setRoundCounter(int roundCounter) {
        GameLogic.roundCounter = roundCounter;
    }

    /*
    Action method analise clicked tile according to current state of map and selected field.
    After analise it get to right method which updates map state .
     */
    public void action(MapPanel tile_, MapPanel[][] mapTiles_) {
        currentPlayer = playersArr[roundCounter % 2];
        Army currentArmy = currentPlayer.getArmy();
        toShow = null;
        if (selected == null && (currentArmy.getTroops().contains(tile_.getEntity_on_tile()) ||
                currentArmy.getBuildings().contains(tile_.getEntity_on_tile()))) {

            System.out.println(1);
            tile_.setBorder(Color.RED, 2);
            tileNotSelected(tile_, mapTiles_);
            selected = tile_;
        } else if (selected != null && !(tile_.equals(selected.getClientProperty("Position")) && tile_.getEntity_on_tile() == null)) {
            System.out.println(2);
            tileSelected(tile_);
            selected.setBorder(Color.BLACK, 0);
            selected = null;


        } else if (selected != null && selected.equals(tile_)) {
            toShow = selected.getEntity_on_tile();
            selected.setBorder(Color.BLACK, 0);
            selected = null;
            clearArrMoves();
            clearArrAttacks();
        } else {
            selected = null;
            System.out.println(3);
            clearArrAttacks();
            clearArrMoves();
        }
    }

    //updates map state, when no tile is selected before
    public void tileNotSelected(MapPanel Tile_, MapPanel[][] mapTiles_) {
        if (Tile_.getEntity_on_tile() instanceof iMovable) {
            setMoveBorders(Tile_, mapTiles_, Color.MAGENTA);
        }
        setAttackBorders(Tile_, mapTiles_, Color.ORANGE);
    }

    /*
    Updades map state, when unit was selected before. Checks if we ordered to move or attack
     */
    public void tileSelected(MapPanel Tile_) {
        if (this.selected.getEntity_on_tile() instanceof iMovable && this.possibleMoves.contains(Tile_) && Tile_ != selected) {
            System.out.println("moving...");
            moveEntity(Tile_, selected);
        } else if (this.possibleAttacks.contains(Tile_) && Tile_ != selected) {
            attackEntity(Tile_);
        }
        this.clearArrAttacks();
        this.clearArrMoves();
    }

    /*
    updates possibleAttacks array, change borders of tiles to highlight possible attacks
     */
    private void setAttackBorders(MapPanel Tile_, MapPanel[][] mapTiles_, Color color_) {
        Entity entityOnTile = Tile_.getEntity_on_tile();
        this.possibleAttacks = new ArrayList<>();
        for (Point point : entityOnTile.getPossible_attacks()) {
            for (int i = 0; i < AppFrame.MAPSIZE; i++) {
                for (int j = 0; j < AppFrame.MAPSIZE; j++) {
                    Point tilePosition = (Point) mapTiles_[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point) && mapTiles_[i][j].isOccupied() && mapTiles_[i][j].getOwner() != Tile_.getOwner()) {
                        mapTiles_[i][j].setBorder(color_, 1);
                        this.possibleAttacks.add(mapTiles_[i][j]);
                    }
                }
            }
        }
    }

    /*
    updates possibleMoves array, change borders of these tiles to highlight possible moves
     */
    private void setMoveBorders(MapPanel Tile_, MapPanel[][] mapTiles_, Color color_) {
        iMovable entityOnTile = (iMovable) Tile_.getEntity_on_tile();
        this.possibleMoves = new ArrayList<>();
        for (Point point : entityOnTile.getPossible_moves()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Point tilePosition = (Point) mapTiles_[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point) && !mapTiles_[i][j].isOccupied()) {
                        mapTiles_[i][j].setBorder(color_, 1);
                        this.possibleMoves.add(mapTiles_[i][j]);
                    }
                }
            }
        }
    }


    /*
    Moves entity which is posisioned on MapPanel selected_ to MapPanel tile_ .
    Asserts unit can move.
     */
    private void moveEntity(MapPanel tile_, MapPanel selected_) {
        GameLogic.roundCounter++;
        MovingUnit entity = (MovingUnit) selected_.getEntity_on_tile();
        entity.Move((Point) tile_.getClientProperty("Position"));
        tile_.setEntity_on_tile(entity);
        tile_.setOwner(selected_.getOwner());
        selected_.removeAll();
        selected_.setEntity_on_tile(null);
        selected_.setOwner(null);
        clearArrMoves();
        clearArrAttacks();

    }

    /*
    Unit on selected tile, attacks and kills Enitity on MapPanel tile_ .
    all Entities can attack .
     */
    private void attackEntity(MapPanel tile_) {
        GameLogic.roundCounter++;
        tile_.getEntity_on_tile().setPosition(null);
        tile_.removeAll();
        tile_.setEntity_on_tile(null);
        tile_.setOwner(null);
        clearArrMoves();
        clearArrAttacks();

    }

    /*
    Clears possibleAttacks array and change borders to normal color.
     */
    public void clearArrAttacks() {
        for (MapPanel panel : possibleAttacks) {
            panel.setBorder(Color.BLACK, 0);
        }
        this.possibleAttacks.clear();
    }

    /*
    Clears possibleMoves array and change borders to normal color.
     */
    public void clearArrMoves() {
        for (MapPanel panel : possibleMoves) {
            panel.setBorder(Color.BLACK, 0);
        }
        this.possibleMoves.clear();
    }

    public ArrayList<MapPanel> getPossibleAttacks() {
        return possibleAttacks;
    }

    //GETTERS AND SETTERS____________________________________________________

    public void setPossibleAttacks(ArrayList<MapPanel> possibleAttacks) {
        this.possibleAttacks = possibleAttacks;
    }

    public ArrayList<MapPanel> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<MapPanel> possibleMoves) {
        this.possibleMoves = possibleMoves;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

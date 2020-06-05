package org.groupTw;

import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.MovingUnit;

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
    private boolean gameState; // is game won?
    private Player winner;


    //constructor
    public GameLogic(Player[] playersArr_) {
        playersArr = playersArr_;
        possibleAttacks = new ArrayList<>();
        possibleMoves = new ArrayList<>();
        currentPlayer = playersArr[roundCounter % 2];
        toShow = null;
        selected = null;
        winner = null;
        gameState = true;
    }

    public static int getRoundCounter() {
        return roundCounter;
    }

    public static void setRoundCounter(int roundCounter) {
        GameLogic.roundCounter = roundCounter;
    }

    /*
    Action method analise clicked tile according to current state of map and selected field.
    After analise it gets to right method which updates map state .
     */
    public void action(MapPanel tile_, MapPanel[][] mapTiles_) {
        currentPlayer = playersArr[roundCounter % 2];
        ArrayList<Entity> currentArmy = currentPlayer.getArmy();
        toShow = null;
        //first click on unit, which is in your army
        if (selected == null && currentArmy.contains(tile_.getEntity_on_tile())) {
            tile_.setBorder(Color.RED, 2);
            tileNotSelected(tile_, mapTiles_);
            selected = tile_;
        //second click on a tile other than selected
        } else if (selected != null && !selected.equals(tile_) ) {
            tileSelected(tile_);
            selected.setBorder(Color.BLACK, 0);
            selected = null;
            updateBoardState();

        //second click on the same tile
        } else if (selected != null && selected.equals(tile_)) {
            toShow = selected.getEntity_on_tile();
            selected.setBorder(Color.BLACK, 0);
            selected = null;
            clearArrMoves();
            clearArrAttacks();
        //for now - first click on a tile without unit, or on a tile which contains not yours unit
        } else {
            selected = null;
            clearArrAttacks();
            clearArrMoves();
        }

    }

    private void updateBoardState(){
        System.out.println(playersArr[ (roundCounter + 1)%2 ].getArmy().size());
        if(playersArr[ (roundCounter + 1)%2 ].getArmy().size() == 0){
            winner = currentPlayer;
            gameState = false;
        }
        for(Player ply : playersArr){
            ply.getGoldPerTurn();
        }
        roundCounter++;
    }
    //updates map state, when no tile is selected before
    private void tileNotSelected(MapPanel Tile_, MapPanel[][] mapTiles_) {
        if (Tile_.getEntity_on_tile() instanceof iMovable) {
            setMoveBorders(Tile_, mapTiles_, Color.MAGENTA);
        }
        setAttackBorders(Tile_, mapTiles_, Color.ORANGE);
    }

    /*
    Updates map state, when unit was selected before. Checks if we ordered to move or attack
     */
    private void tileSelected(MapPanel Tile_) {
        //checks if unit can move, if it cans checks if its possible to move to the selected tile
        if (this.selected.getEntity_on_tile() instanceof iMovable && this.possibleMoves.contains(Tile_) && Tile_ != selected) {
            moveEntity(Tile_, selected);
        //every entity can attack, so it only checks if tile is possible to attack
        } else if (this.possibleAttacks.contains(Tile_) && Tile_ != selected) {
            attackEntity(Tile_);
        }
        //clear highlights
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
    Moves entity which is positioned on MapPanel selected_ to MapPanel tile_ .
    Asserts unit can move.
     */
    private void moveEntity(MapPanel tile_, MapPanel selected_) {
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
    Unit on a selected tile attacks and kills Entity on MapPanel tile_ .
    all Entities can attack .
     */
    private void attackEntity(MapPanel tile_) {

        int attackValue = selected.getEntity_on_tile().getAttack();
        if(!tile_.getEntity_on_tile().getDamage(attackValue)){

            Player ply = this.playersArr[ (roundCounter+1)%2 ]; //get owner of killed unit
            ply.getArmy().remove(tile_.getEntity_on_tile());

            tile_.removeAll();
            tile_.setEntity_on_tile(null);
            tile_.setOwner(null);

        }
        clearArrMoves();
        clearArrAttacks();


    }

    /*
    Clears possibleAttacks array and change borders to normal color.
     */
    private void clearArrAttacks() {
        for (MapPanel panel : possibleAttacks) {
            panel.setBorder(Color.BLACK, 0);
        }
        this.possibleAttacks.clear();
    }

    /*
    Clears possibleMoves array and change borders to normal color.
     */
    private void clearArrMoves() {
        for (MapPanel panel : possibleMoves) {
            panel.setBorder(Color.BLACK, 0);
        }
        this.possibleMoves.clear();
    }


    //GETTERS AND SETTERS____________________________________________________

    public MapPanel getSelected() {
        return selected;
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

    public Entity getToShow() {
        return toShow;
    }

    public void setToShow(Entity toShow) {
        this.toShow = toShow;
    }

    public void setSelected(MapPanel selected) {
        this.selected = selected;
    }

    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

}

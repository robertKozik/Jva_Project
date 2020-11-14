package org.groupTw;

import org.apache.commons.lang3.SerializationException;
import org.groupTw.MapEnitites.Building;
import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.MovingUnit;
import org.groupTw.MapEnitites.UnitEnum;

import java.awt.*;

public class CreatorLogic implements iLogic {
    private Player[] playersArr;
    private MapPanel firstClick = null; //previously clicked tile

    final public int MAX_ARMY_SIZE = 5;


    public CreatorLogic(Player[] playersArr) {
        this.playersArr = playersArr;
    }

    @Override
    public Player[] getPlayersArr() {
        return this.playersArr;
    }

    /**
     * action method handles all action invoked by mouse click, checks if it's second click on the same tile, if empny
     * tile was clicked, or if unit of current active player was clicked.
     *
     * @param tile_     clicked tile on game map - MapPanel
     * @param mapTiles_ whole map, double-dim array of MapPanels
     */
    @Override
    public void action(MapPanel tile_, MapPanel[][] mapTiles_) {
        try {
            if (CreatorMap.entityToPlace != -1 && tile_.entity_on_tile == null) // index -1 means that no unit is selected
            {
                //player1
                if (CreatorMap.entityToPlace < CreatorMap.prototypes.size() / 2 //if chosen unit index is less than a half of list size, then it belongs to player 1
                        && tile_.getY() < GameLayout.MAPDIM / 2 //player 1 can deploy his units only on his half of map
                        && playersArr[0].getArmy().size() < MAX_ARMY_SIZE)  //player can only have MAX-ARMY_SIZE units deployed
                {

                    Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone(); //can throw SeriazlizatonException - deep clone
                    klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));  //change position of cloned entity

                    if (klon instanceof MovingUnit) {
                        ((MovingUnit) klon).evaluatePatterns();//changes attackPattern and movePattern after changing its position
                    } else {
                        ((Building) klon).evaluatePatterns(); //changes attackPattern of clone
                    }
                    playersArr[0].getArmy().add(klon);
                    tile_.setEntity_on_tile(klon);

                }
                //player2
                else if (CreatorMap.entityToPlace >= CreatorMap.prototypes.size() / 2
                        && tile_.getY() >= GameLayout.MAPDIM / 2 && playersArr[1].getArmy().size() < MAX_ARMY_SIZE) {

                    Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone();//can throw SeriazlizatonException - deep clone
                    klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));//change position of cloned entity
                    if (klon instanceof MovingUnit) {
                        ((MovingUnit) klon).evaluatePatterns();//changes attackPattern and movePattern after changing its position
                    } else {
                        ((Building) klon).evaluatePatterns();//changes attackPattern of clone

                    }
                    playersArr[1].getArmy().add(klon);
                    tile_.setEntity_on_tile(klon);


                }
                if (CreatorMap.selected != null) {
                    CreatorMap.selected.setBorder(null); //change border style
                }
                CreatorMap.selected = null;
                CreatorMap.entityToPlace = -1;
            } else {
                //Saving first click
                if (firstClick == null && tile_.isOccupied()) {
                    firstClick = tile_;
                }
                //Second click on empty tile
                else if (firstClick != null && !tile_.isOccupied()) {
                    Entity entity = firstClick.getEntity_on_tile();
                    entity.setPosition((Point) tile_.getClientProperty("Position"));
                    tile_.setEntity_on_tile(entity);
                    tile_.setOwner(firstClick.getOwner());
                    firstClick.removeAll();
                    firstClick.setEntity_on_tile(null);
                    firstClick.setOwner(null);
                    firstClick = null;
                }
                //Second click on same tile (removing entity)
                else if (firstClick != null && tile_.entity_on_tile.equals(firstClick.entity_on_tile)) {
                    if (firstClick.entity_on_tile.getColor() == UnitEnum.BLUE) {
                        playersArr[0].getArmy().remove(firstClick.entity_on_tile);
                    } else {
                        playersArr[1].getArmy().remove(firstClick.entity_on_tile);
                    }
                    firstClick.removeAll();
                    firstClick.setEntity_on_tile(null);
                    firstClick.setOwner(null);
                    firstClick = null;
                }
                //second click on same color entity(swapping places)
                else if (firstClick != null && tile_.entity_on_tile.getColor().equals(firstClick.entity_on_tile.getColor())) {
                    Entity entity = firstClick.getEntity_on_tile();
                    Entity temp = tile_.getEntity_on_tile().clone();
                    entity.setPosition((Point) tile_.getClientProperty("Position"));
                    temp.setPosition((Point) firstClick.getClientProperty("Position"));
                    tile_.setEntity_on_tile(entity);
                    tile_.setOwner(firstClick.getOwner());
                    firstClick.setEntity_on_tile(temp);
                    firstClick.setOwner(firstClick.getOwner());
                    firstClick = null;
                }
            }
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }
}

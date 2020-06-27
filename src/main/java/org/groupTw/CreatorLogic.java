package org.groupTw;

import org.groupTw.MapEnitites.Building;
import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.MovingUnit;

import java.awt.*;

public class CreatorLogic implements iLogic {
    private Player[] playersArr;
    final private int MaxStartingSizeOfArmy = 5;
    public CreatorLogic(Player[] playersArr) {
        this.playersArr = playersArr;
    }
    private MapPanel firstClick=null;

    @Override
    public Player[] getPlayersArr() {
        return this.playersArr;
    }

    @Override
    public void action(MapPanel tile_, MapPanel[][] mapTiles_) {
        System.out.println(playersArr[0].getArmy());
        try {
        if (CreatorMap.entityToPlace != -1 && tile_.entity_on_tile == null) {
            //player1
                if (CreatorMap.entityToPlace < CreatorMap.prototypes.size() / 2 && tile_.getY() < GameLayout.MAPDIM / 2
                        && playersArr[0].getArmy().size() < MaxStartingSizeOfArmy) {

                    Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone();

                    klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));
                    System.out.println(klon.getPosition().toString());
                    if (klon instanceof MovingUnit)
                        ((MovingUnit) klon).evaluatePatterns();
                    else
                        ((Building) klon).evaluatePatterns();
                    playersArr[0].getArmy().add(klon);
                    tile_.setEntity_on_tile(klon);

                }
                //player2
                else if (CreatorMap.entityToPlace >= CreatorMap.prototypes.size() / 2
                        && tile_.getY() >= GameLayout.MAPDIM / 2 && playersArr[1].getArmy().size() < MaxStartingSizeOfArmy) {

                    Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone();
                    klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));
                    if (klon instanceof MovingUnit)
                        ((MovingUnit) klon).evaluatePatterns();
                    else
                        ((Building) klon).evaluatePatterns();
                    playersArr[1].getArmy().add(klon);
                    tile_.setEntity_on_tile(klon);

                }
            CreatorMap.selected.setBorder(null);
            CreatorMap.selected=null;
            CreatorMap.entityToPlace=-1;
        }
        else{
            //Saving first click
            if (firstClick==null && tile_.isOccupied()){
                    firstClick=tile_;
            }
            //Second click on empty tile
            else if (firstClick!=null && !tile_.isOccupied()){
                MovingUnit entity = (MovingUnit) firstClick.getEntity_on_tile();
                entity.Move((Point) tile_.getClientProperty("Position"));
                tile_.setEntity_on_tile(entity);
                tile_.setOwner(firstClick.getOwner());
                firstClick.removeAll();
                firstClick.setEntity_on_tile(null);
                firstClick.setOwner(null);
                firstClick=null;
            }
            //Second click on same tile (removing entity)
            else if(firstClick!=null && tile_.entity_on_tile.equals(firstClick.entity_on_tile)){
                if (firstClick.entity_on_tile.getColor().equals("blue"))
                    playersArr[0].getArmy().remove(firstClick.entity_on_tile);
                else
                    playersArr[1].getArmy().remove(firstClick.entity_on_tile);
                firstClick.removeAll();
                firstClick.setEntity_on_tile(null);
                firstClick.setOwner(null);
                firstClick=null;
            }
            //second click on same color entity(swaping places)
            else if(firstClick!=null && tile_.entity_on_tile.getColor().equals(firstClick.entity_on_tile.getColor())){
                MovingUnit entity = (MovingUnit) firstClick.getEntity_on_tile();
                MovingUnit temp = (MovingUnit) tile_.getEntity_on_tile().clone();
                entity.Move((Point) tile_.getClientProperty("Position"));
                temp.Move((Point)firstClick.getClientProperty("Position"));
                tile_.setEntity_on_tile(entity);
                tile_.setOwner(firstClick.getOwner());
                firstClick.setEntity_on_tile(temp);
                firstClick.setOwner(firstClick.getOwner());
                firstClick=null;
            }
        }
    }catch (Exception e) {
            e.printStackTrace();
    }
    }
}

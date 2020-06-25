package org.groupTw;

import org.groupTw.MapEnitites.Building;
import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.MovingUnit;

import java.awt.*;
import java.lang.Cloneable;

public class CreatorLogic implements iLogic {
    private Player[] playersArr;
    private Entity toPlace;


    public CreatorLogic(Player[] playersArr) {
        this.playersArr = playersArr;
    }

    @Override
    public Player[] getPlayersArr() {
        return this.playersArr;
    }

    @Override
    public void action(MapPanel tile_, MapPanel[][] mapTiles_)
            throws CloneNotSupportedException {
        if (CreatorMap.entityToPlace != -1 && tile_.entity_on_tile == null) {
            //player1
            if (CreatorMap.entityToPlace < CreatorMap.prototypes.size() / 2 && tile_.getY() < GameLayout.MAPDIM / 2) {
                Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone();
                klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));
                System.out.println(klon.getPosition().toString());
                if(klon instanceof MovingUnit)
                    ((MovingUnit) klon).evaluatePatterns();
                else
                    ((Building) klon).evaluatePatterns();
                playersArr[0].getArmy().add(klon);
                tile_.setEntity_on_tile(klon);
            }
            //player2
            else if (CreatorMap.entityToPlace >= CreatorMap.prototypes.size() / 2 && tile_.getY() >= GameLayout.MAPDIM / 2) {
                Entity klon = CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone();
                klon.setPosition(new Point(tile_.getY() / 60, tile_.getX() / 60));
                if(klon instanceof MovingUnit)
                    ((MovingUnit) klon).evaluatePatterns();
                else
                    ((Building) klon).evaluatePatterns();
                playersArr[1].getArmy().add(klon);
                tile_.setEntity_on_tile(klon);
            }

        }
    }
}

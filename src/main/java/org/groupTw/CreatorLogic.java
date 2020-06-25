package org.groupTw;

import org.groupTw.MapEnitites.Entity;

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
            tile_.setEntity_on_tile(CreatorMap.prototypes.get(CreatorMap.entityToPlace).clone());
        }
    }
}

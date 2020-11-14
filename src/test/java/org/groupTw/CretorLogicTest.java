package org.groupTw;

import org.groupTw.MapEnitites.EntityFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CretorLogicTest {


    @Test
    public void testUnitPlacement()
    {
        Player[] plyArr = new Player[2];
        for(int i=0; i<2; i++)
            plyArr[i] = new Player();//init new players
        AppFrame.setPlayersArr(plyArr);
        CreatorLogic logic = new CreatorLogic(AppFrame.getPlayersArr());
        GameLayout map = new GameLayout(logic);
        CreatorMap creatorMap = new CreatorMap(map);
        EntityFactory factory = new EntityFactory();
        CreatorMap.entityToPlace = 0 ;// Entity to place is blue archer
        logic.action(map.mapTiles[0][0], map.mapTiles);
        //logic.action(Map.mapTiles[1][1],Map.mapTiles);
        assertEquals("archerblue", map.mapTiles[0][0].getEntity_on_tile().toString());
    }
}

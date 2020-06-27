package org.groupTw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.groupTw.MapEnitites.EntityFactory;
import org.junit.Test;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class TestAttackEntity
{
    private AppFrame appFrame;
    private iLogic logic;
    private GameLayout mainGame;
    @Test
    public void testAttackEntity()
    {
        appFrame =  new AppFrame();
        logic = new GameLogic(appFrame.playersArr);
        mainGame = new GameLayout(logic);
        EntityFactory factory = new EntityFactory();
        logic.getPlayersArr()[0].getArmy().add(factory.addEntity("MERCENARY", new Point(0,0), "blue"));
        logic.getPlayersArr()[1].getArmy().add(factory.addEntity("MERCENARY", new Point(2,2), "red"));
        mainGame.mapTiles[0][0].setEntity_on_tile(appFrame.playersArr[0].getArmy().get(0));
        mainGame.mapTiles[0][0].setOwner(logic.getPlayersArr()[0]);
        mainGame.mapTiles[2][2].setEntity_on_tile(appFrame.playersArr[1].getArmy().get(0));
        mainGame.mapTiles[2][2].setOwner(logic.getPlayersArr()[1]);
        logic.action(mainGame.mapTiles[0][0],mainGame.mapTiles);
        logic.action(mainGame.mapTiles[1][1],mainGame.mapTiles);
        assertTrue(logic.getPlayersArr()[0].getArmy().size()==1);
        assertFalse(logic.getPlayersArr()[1].getArmy().size()==0);
    }
}

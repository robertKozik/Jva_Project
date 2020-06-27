package org.groupTw;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.groupTw.MapEnitites.EntityFactory;
import org.junit.Test;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class TestMoveEntity
{
    private AppFrame appFrame;
    private iLogic logic;
    private GameLayout mainGame;
    @Test
    public void testMoveEntity()
    {
        appFrame =  new AppFrame();
        logic = new GameLogic(appFrame.playersArr);
        mainGame = new GameLayout(logic);
        EntityFactory factory = new EntityFactory();
        logic.getPlayersArr()[0].getArmy().add(factory.addEntity("MERCENARY", new Point(0,0), "blue"));
        mainGame.mapTiles[0][0].setEntity_on_tile(appFrame.playersArr[0].getArmy().get(0));
        mainGame.mapTiles[0][0].setOwner(logic.getPlayersArr()[0]);
        logic.action(mainGame.mapTiles[0][0],mainGame.mapTiles);
        logic.action(mainGame.mapTiles[1][1],mainGame.mapTiles);
        assertFalse(logic.getPlayersArr()[0].getArmy().get(0).getPosition()==new Point(0,0));

    }
}

package org.groupTw;

import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.UnitEnum;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertNotSame;

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
        logic = new GameLogic(AppFrame.getPlayersArr());
        mainGame = new GameLayout(logic);

        EntityFactory factory = new EntityFactory();
        logic.getPlayersArr()[0].getArmy().add(factory.addEntity(UnitEnum.MERCENARY, new Point(0, 0), UnitEnum.BLUE));
        mainGame.mapTiles[0][0].setEntity_on_tile(AppFrame.getPlayersArr()[0].getArmy().get(0));
        mainGame.mapTiles[0][0].setOwner(logic.getPlayersArr()[0]);
        logic.action(mainGame.mapTiles[0][0],mainGame.mapTiles);
        logic.action(mainGame.mapTiles[1][1],mainGame.mapTiles);
        assertNotSame(logic.getPlayersArr()[0].getArmy().get(0).getPosition(), new Point(0, 0));

    }
}

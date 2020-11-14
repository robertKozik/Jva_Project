package org.groupTw;

import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.UnitEnum;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test for simple App.
 */
public class TestAttackEntity
{
    private AppFrame appFrame;
    private iLogic logic;
    private GameLayout mainGame;

    @Test
    public void testAttackEntity() {
        appFrame = new AppFrame();
        logic = new GameLogic(AppFrame.getPlayersArr());
        mainGame = new GameLayout(logic);
        EntityFactory factory = new EntityFactory();
        logic.getPlayersArr()[0].getArmy().add(factory.addEntity(UnitEnum.MERCENARY, new Point(0, 0), UnitEnum.BLUE));
        logic.getPlayersArr()[1].getArmy().add(factory.addEntity(UnitEnum.MERCENARY, new Point(2, 2), UnitEnum.RED));
        mainGame.mapTiles[0][0].setEntity_on_tile(AppFrame.getPlayersArr()[0].getArmy().get(0));
        mainGame.mapTiles[0][0].setOwner(logic.getPlayersArr()[0]);
        mainGame.mapTiles[2][2].setEntity_on_tile(AppFrame.getPlayersArr()[1].getArmy().get(0));
        mainGame.mapTiles[2][2].setOwner(logic.getPlayersArr()[1]);
        logic.action(mainGame.mapTiles[0][0], mainGame.mapTiles);
        logic.action(mainGame.mapTiles[1][1], mainGame.mapTiles);
        assertEquals(1, logic.getPlayersArr()[0].getArmy().size());
        assertNotEquals(0, logic.getPlayersArr()[1].getArmy().size());
    }

}

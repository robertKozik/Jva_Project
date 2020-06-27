package org.groupTw;

import junit.framework.TestCase;
import org.groupTw.MapEnitites.Archer;
import org.junit.Assert;

import java.awt.*;

public class GameLogicTest extends TestCase {

    public void testAttackEntity() {
        MapPanel mapPanel = new MapPanel();
        Archer archer = new Archer(new Point(1, 2), "RED");
        mapPanel.setEntity_on_tile(archer);
        //System.out.println(entity.getCurrentHealth());
        Player[] playersArr = new Player[2];
        for (int i = 0; i < 2; i++)
            playersArr[i] = new Player();
        mapPanel.setOwner(playersArr[1]);
        GameLogic gameLogic = new GameLogic(playersArr);
        gameLogic.setSelected(mapPanel);
        //System.out.println(mapPanel.getEntity_on_tile());
        //System.out.println(mapPanel.getOwner());
        gameLogic.attackEntity(mapPanel);
        //System.out.println(mapPanel.getEntity_on_tile());
        //System.out.println(mapPanel.getOwner());
        Assert.assertEquals(mapPanel.getEntity_on_tile(), null);
        Assert.assertNotEquals(mapPanel.getOwner(), true);
    }

}
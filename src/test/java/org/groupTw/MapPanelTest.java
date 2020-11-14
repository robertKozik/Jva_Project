package org.groupTw;

import org.groupTw.MapEnitites.Archer;
import org.groupTw.MapEnitites.ColorEnum;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class MapPanelTest {

    @Test
    public void TilesTestEquals() {
        MapPanel mapPanel0 = new MapPanel();
        MapPanel mapPanel1 = new MapPanel();
        MapPanel mapPanel2 = new MapPanel();
        mapPanel0.putClientProperty("Position", new Point(3, 6));
        mapPanel1.putClientProperty("Position", new Point(3, 6));
        mapPanel2.putClientProperty("Position", new Point(0, 0));
        Assert.assertTrue(mapPanel0.equals(mapPanel1));
        Assert.assertFalse(mapPanel1.equals((mapPanel2)));
    }

    public void testIsOccupied() {
        MapPanel mapPanel = new MapPanel();
        Assert.assertFalse(mapPanel.isOccupied());

        mapPanel.setEntity_on_tile(new Archer(new Point(1, 1), ColorEnum.RED));

        Assert.assertTrue(mapPanel.isOccupied());

    }
}
package org.groupTw;

import junit.framework.TestCase;
import org.junit.Assert;

import java.awt.*;

public class MapPanelTest extends TestCase {

    public void testTestEquals() {
        MapPanel mapPanel0 = new MapPanel();
        MapPanel mapPanel1 = new MapPanel();
        MapPanel mapPanel2 = new MapPanel();
        mapPanel0.putClientProperty("Position", new Point(3, 6));
        mapPanel1.putClientProperty("Position", new Point(3, 6));
        mapPanel2.putClientProperty("Position", new Point(0, 0));
        Assert.assertTrue(mapPanel0.equals(mapPanel1));
        Assert.assertFalse(mapPanel1.equals((mapPanel2)));
    }
}
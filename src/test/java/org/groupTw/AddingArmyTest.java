package org.groupTw;

import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.UnitEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddingArmyTest {

    @Test
    public void addUnitToArmy() {
        Player ply = new Player();
        EntityFactory factory = new EntityFactory();
        ply.getArmy().add(factory.addEntity(UnitEnum.WARRIOR, UnitEnum.RED));

        assertEquals(ply.getArmy().size(), 1);
    }
}

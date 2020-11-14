package org.groupTw;

import org.groupTw.MapEnitites.ColorEnum;
import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.UnitEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DamageTest {
    Entity entity;

    @Test
    public void testDamageDeal(){
        EntityFactory factory = new EntityFactory();

        entity = factory.addEntity(UnitEnum.WARRIOR, ColorEnum.RED);
        entity.getDamage(10);

        assertEquals(10,entity.getCurrentHealth());
    }


}

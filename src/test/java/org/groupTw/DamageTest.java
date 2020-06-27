package org.groupTw;

import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.EntityFactory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DamageTest {
    Entity entity;

    @Test
    public void testDamageDeal(){
        EntityFactory factory = new EntityFactory();

        entity = factory.addEntity("warrior", "blue");
        entity.getDamage(10);

        assertEquals(10,entity.getCurrentHealth());
    }


}

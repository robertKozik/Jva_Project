package org.groupTw;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class GeneratingPlayerNameTest {

    @Test
    public void playerNamesEquals() {
        Player ply1 = new Player();
        Player ply2 = new Player();

        assertNotEquals(ply1.getPlayerName(), ply2.getPlayerName());
    }

}

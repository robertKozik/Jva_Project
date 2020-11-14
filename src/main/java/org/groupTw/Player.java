package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents player
 */
public class Player {
    /**
     * Stores all Entities of player
     */
    ArrayList<Entity> army;
    int gold;
    String playerName;

    /**
     * Creates player with empty army and random playerName
     */
    public Player() {
        army = new ArrayList<>();
        gold = 0;
        playerName = "Player-" + new Random().nextInt(1000);
    }

    /**
     * Creates a player with given name, army and gold
     *
     * @param playerName_ String which contains desired name of player
     * @param army_       ArrayList of units
     */
    public Player(String playerName_, ArrayList<Entity> army_, int gold_) {
        army = army_;
        this.gold = gold_;
        this.playerName = playerName_;
    }

    /**
     * Creates a player with given name
     *
     * @param playerName_ desired name
     */
    public Player(String playerName_) {
        army = new ArrayList<>();
        this.gold = 0;
        this.playerName = playerName_;
    }

    /**
     * Updates player gold field
     */
    public void getGoldPerTurn() {
        this.gold += 100;
    }

    //Getters and setters:

    public ArrayList<Entity> getArmy() {
        return army;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

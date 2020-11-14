package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    ArrayList<Entity> army;
    int gold;
    String playerName;

    public Player(){
        army = new ArrayList<>();
        gold = 0;
        playerName = "Player-" + new Random().nextInt(1000);
    }
    public Player(String playerName_, ArrayList<Entity> army_, int gold_){
        army = army_;
        this.gold = gold_;
        this.playerName = playerName_;
    }

    public Player(String playerName_){
        army = new ArrayList<>();
        this.gold = 0;
        this.playerName = playerName_;
    }

    public void getGoldPerTurn(){
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

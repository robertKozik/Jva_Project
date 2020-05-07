package org.groupTw;

import java.util.Random;

public class Player {
    Army army;
    int gold;
    String playerName;

    public Player(){
        army = new Army();
        gold = 0;
        playerName = "Player"+ String.valueOf(new Random().nextInt(1000));
    }
    public Player(String playerName_, Army army_, int gold_){
        this.army = army_;
        this.gold = gold_;
        this.playerName = playerName_;
    }

    public Player(String playerName_){
        this.army = new Army();
        this.gold = 0;
        this.playerName = playerName_;
    }

    //Getters and setters:

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

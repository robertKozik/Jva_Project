package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import java.util.ArrayList;

public class Army {
    private ArrayList<Entity>troops;
    private ArrayList<Entity>buildings;

    public Army(){
        this.troops = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public void addTroop(Entity entity_){

    }

    public Army(ArrayList<Entity> troops_) {
        this.troops = troops_;
    }

    public boolean remove(Entity ent_){
        return this.troops.remove(ent_);
    }

    //getters adn setters:

    public ArrayList<Entity> getTroops() {
        return troops;
    }

    public void setTroops(ArrayList<Entity> troops) {
        this.troops = troops;
    }

    public ArrayList<Entity> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Entity> buildings) {
        this.buildings = buildings;
    }
}

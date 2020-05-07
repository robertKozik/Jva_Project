package org.groupTw;

import java.util.ArrayList;

public class Army {
    private ArrayList<Entity>troops;

    public Army(){
        this.troops = new ArrayList<>();
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
}

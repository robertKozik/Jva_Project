package org.groupTw;
/*
1.
Obiekt wywołujący
1.AppFrame          Entity Factory
    |                  n
    v                  |
2.GameLayout <- Warrior Archer  <-Entity
     n                      |
     |                      v
 GameLogic, MapPane      interfejs iMovable
 */
public class Main {
    public static void main(String[] args){
        AppFrame app = new AppFrame();
        app.setVisible(true);
    }
}

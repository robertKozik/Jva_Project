package org.groupTw;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    final public static int MAPSIZE = 10;
    final public static int FRAMEYSPAN = 720;
    final public static int FRAMEXSPAN = 700;
    private Player[] playersArr;
    private iLogic logic;
    private Menu menu;
    private GameLayout mainGame;


    public AppFrame () {
        menu = new Menu();
        mainGame = null;
        this.playersArr = new Player[2];
        for(int i=0; i<2; i++)
            this.playersArr[i] = new Player();//init new players
        this.setSize(FRAMEXSPAN, FRAMEYSPAN);
        this.setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateFrame("MENU");
        }


    void updateFrame(String name_){
        String upperCase = name_.toUpperCase();
        switch (upperCase)
        {
            case "MENU":
                this.repaint();
                this.add(menu);
                setVisible(true);
                break;
            case "NEW GAME":
                this.remove(menu);
                logic = new GameLogic(this.playersArr);
                mainGame = new GameLayout(logic);
                this.add(mainGame);
                setVisible(true);
                break;
            case "ENDGAME":
                this.remove(mainGame);
                updateFrame("MENU");
        }
    }

}

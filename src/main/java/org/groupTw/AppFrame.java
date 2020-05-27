package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppFrame extends JFrame {
    final static int MAPSIZE = 8;
    final static int YSPAN = 520;
    final static int XSPAN = 500;
    private Player[] playersArr;
    protected MapPanel[][] map;
    private iLogic logic;
    private Menu menu;


    public AppFrame () {
        menu = new Menu();
        this.playersArr = new Player[2];
        for(int i=0; i<2; i++)
            this.playersArr[i] = new Player();//init new players
        this.logic=new GameLogic(playersArr);
        this.setSize(XSPAN,YSPAN);
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
                this.add(menu);
                setVisible(true);
                break;
            case "NEW GAME":
                this.remove(menu);
                logic = new GameLogic(this.playersArr);
                GameLayout Map = new GameLayout(logic);
                this.add(Map);
                setVisible(true);
                break;
        }
    }

}

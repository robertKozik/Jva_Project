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
    private GameLayout Map;
    private JPanel settingsPanel;
    private Player[] playersArr;
    private GameLogic logic;
    private MapPanel selected;
    private JPanel mapPanel;
    private MapPanel[][] mapTiles;
    private int roundCounter;
    private Menu menu;
    private JTabbedPane tabs;


    public AppFrame () {
        this.roundCounter=0;
        this.mapTiles= new MapPanel[MAPSIZE][MAPSIZE];
        this.mapPanel = new JPanel();
        this.selected = null;
        this.playersArr = new Player[2];
        for(int i =0; i<2; i++) this.playersArr[i] = new Player();//init new players
        init_2();
        }

    private void init(){
        this.logic=new GameLogic(this.roundCounter);
        this.setSize(1000,520);

        this.setLayout(new GridLayout(1,2));

        settingsPanel = new JPanel();
        settingsPanel.add(new JLabel(String.valueOf(GameLogic.getRoundCounter())));
        this.add(settingsPanel);

        Map = new GameLayout(this.playersArr, this.logic,this.selected,this.mapPanel,this.mapTiles);
        this.add(Map);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

    private void init_2(){

        this.logic=new GameLogic(this.roundCounter);
        this.setSize(XSPAN,YSPAN);
        this.setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new Menu();
        this.add(menu);
        this.setVisible(true);



    }

    void updateFrame(String name_){
        String upperCase = name_.toUpperCase();
        switch (upperCase)
        {
            case "NEW GAME":
                this.remove(menu);
                Map = new GameLayout(this.playersArr, this.logic,this.selected,this.mapPanel,this.mapTiles);
                this.add(Map);
                setVisible(true);
        }
    }

}

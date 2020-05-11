package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppFrame extends JFrame {
    final static int MapSize = 8;
    private JPanel layout;
    private GameLayout Map;
    private JPanel settingsPanel;
    private Player[] playersArr;
    private GameLogic logic;
    private MapPanel selected;
    private JPanel mapPanel;
    private MapPanel[][] mapTiles;
    private int roundCounter;


    public AppFrame () {
        this.roundCounter=0;
        this.mapTiles= new MapPanel[MapSize][MapSize];
        this.mapPanel = new JPanel();
        this.selected = null;
        this.playersArr = new Player[2];
        for(int i =0; i<2; i++) this.playersArr[i] = new Player();//init new players
        init(); //inicjujesz na początku menu(może być prywatną klasą tutaj) która ma przyciski, po kliknięciu na "start game" usuwasz menu z widoku i dodajesz mapę
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


}

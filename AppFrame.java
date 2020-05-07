package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppFrame extends JFrame {
    private JPanel layout;
    private GameLayout Map;
    private JPanel settingsPanel;
    private Player[] playersArr;

    public AppFrame () {
        this.playersArr = new Player[2];
        for(int i =0; i<2; i++) this.playersArr[i] = new Player();//init new players
        init(); //inicjujesz na początku menu(może być prywatną klasą tutaj) która ma przyciski, po kliknięciu na "start game" usuwasz menu z widoku i dodajesz mapę
    }

    private void init(){
        this.setSize(1000,520);

        this.setLayout(new GridLayout(1,2));

        settingsPanel = new JPanel();
        settingsPanel.add(new JLabel(String.valueOf(GameLogic.getRoundCounter())));
        this.add(settingsPanel);

        Map = new GameLayout(this.playersArr, new GameLogic());
        this.add(Map);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }


}

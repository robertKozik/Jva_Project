package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
główny obiekt programu, dziedziczy po Ramce JFrame, w niej znajdą się wszystkie komponenty
 */
public class AppFrame extends JFrame {
    private JPanel layout;
    private GameLayout Map;
    private JPanel settingsPanel;

    public AppFrame () {
        init();
    }

    private void init(){
        this.setSize(1000,520);

        this.setLayout(new GridLayout(1,2));

        settingsPanel = new JPanel();
        settingsPanel.add(new JLabel(String.valueOf(GameLogic.getRoundCounter())));
        this.add(settingsPanel);

        Map = new GameLayout();
        this.add(Map);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }


}

package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreBoard extends JPanel {

    JPanel secondaryLayout;
    JLabel winnerName;
    JButton returnButton;

    public ScoreBoard(){
        secondaryLayout = new JPanel();
        winnerName = new JLabel();
        returnButton = new JButton();
        init();
    }

    private void init(){
        //init primary layout
        this.setLayout(new FlowLayout());
        //this.add(new JPanel());
        //init secondary layout
        secondaryLayout.setLayout(new BoxLayout(secondaryLayout, BoxLayout.Y_AXIS));
        //secondaryLayout.add(new JPanel());
        this.add(secondaryLayout);

        //init winner name label
        winnerName.setText(GameLogic.winner.getPlayerName());
        //winnerName.setPreferredSize( new Dimension(300,100));
        secondaryLayout.add(winnerName);

        //init return button
        returnButton.addActionListener(e -> sentToFrame("ENDGAME"));
        returnButton.setText("RETURN");
        secondaryLayout.add(returnButton);
    }

    private void sentToFrame(String action_){
        AppFrame ancestorFrame = (AppFrame)SwingUtilities.getWindowAncestor(this);
        ancestorFrame.updateFrame(action_);

    }

}

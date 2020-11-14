package org.groupTw;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame displayed after end of the game. Informs user who won the game
 */
public class ScoreBoard extends JPanel {

    JPanel secondaryLayout;
    JLabel winnerName;
    JButton returnButton;

    /**
     * Creates ScoreBoard and initializes it
     */
    public ScoreBoard() {
        secondaryLayout = new JPanel();
        winnerName = new JLabel();
        returnButton = new JButton();
        init();
    }

    /**
     * Inits primary and secondary layout, gets and sets winner name, init return to menu button
     */
    private void init() {
        //init primary layout
        this.setLayout(new FlowLayout());
        //init secondary layout
        secondaryLayout.setLayout(new BoxLayout(secondaryLayout, BoxLayout.Y_AXIS));
        secondaryLayout.add(new JPanel());
        this.add(secondaryLayout);

        //init winner name label
        winnerName.setText("Winner is: " + GameLogic.winner.getPlayerName());
        //winnerName.setPreferredSize( new Dimension(300,100));
        secondaryLayout.add(winnerName);

        //init return button
        returnButton.addActionListener(e -> sentToFrame(FramesEnum.ENDGAME));
        returnButton.setText("RETURN");
        secondaryLayout.add(returnButton);
    }

    private void sentToFrame(FramesEnum action_) {
        AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(this);
        ancestorFrame.updateFrame(action_);

    }

}

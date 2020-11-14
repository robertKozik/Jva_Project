package org.groupTw;

import javax.swing.*;
import java.awt.*;

/**
 * Panel represents Settings. Allows User to change player names and return to Main Menu.
 */
public class SettingsMenu extends JPanel {
    /**
     * Json file which stores players name.
     */
    private JSON json;

    /**
     * Creates Settings menu, inits its layouts and elements with default size and values
     */
    public SettingsMenu() {

        json = new JSON("/nicks.txt");
        JPanel firstScreen = new JPanel();
        JPanel secondScreen = new JPanel();
        JPanel thirdScreen = new JPanel();

        this.add(firstScreen);
        this.add(secondScreen);
        this.add(thirdScreen);

        secondScreen.setBackground(Color.LIGHT_GRAY);

        firstScreen.setMaximumSize(new Dimension(2 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        firstScreen.setPreferredSize(new Dimension(2 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        secondScreen.setMaximumSize(new Dimension(5 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        secondScreen.setPreferredSize(new Dimension(5 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        thirdScreen.setMaximumSize(new Dimension(2 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        thirdScreen.setPreferredSize(new Dimension(2 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));


        /* add back button */
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(thirdScreen);

            ancestorFrame.updateFrame(FramesEnum.MENU);
        });
        thirdScreen.add(backButton);

        JTextField nick0 = new JTextField("Player 1:");
        JTextField nick1 = new JTextField("Player 2:");
        String p0 = json.JSONReadFromFile("player1");
        String p1 = json.JSONReadFromFile("player2");

        JEditorPane nickChange0 = new JEditorPane();
        JEditorPane nickChange1 = new JEditorPane();

        nickChange0.setText(p0);
        nickChange1.setText(p1);

        /* add save button */
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            json.JSONWriteToFile();
            json.JSONAddValue("player1", nickChange0.getText());
            json.JSONAddValue("player2", nickChange1.getText());
            json.JSONCloseWithSave();


        });
        thirdScreen.add(saveButton);

        JPanel firstNick = new JPanel();
        firstNick.setBackground(Color.LIGHT_GRAY);
        firstNick.add(nick0);
        firstNick.add(nickChange0);
        secondScreen.add(firstNick);
        nick0.setEditable(false);

        JPanel secondNick = new JPanel();
        secondNick.setBackground(Color.LIGHT_GRAY);
        secondNick.add(nick1);
        secondNick.add(nickChange1);
        secondScreen.add(secondNick);
        nick1.setEditable(false);


    }

}

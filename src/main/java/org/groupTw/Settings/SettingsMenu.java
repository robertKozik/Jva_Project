package org.groupTw.Settings;

import org.groupTw.AppFrame;
import org.groupTw.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel {

    public SettingsMenu() {

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

        /* add scrollbar */
        //JPanel scrollScreen = new JPanel();
        //test.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9,2000));
//        JScrollPane scrollFrame = new JScrollPane(scrollScreen);
//        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollFrame.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
//        secondScreen.add(scrollFrame);
//        scrollScreen.setLayout(new BoxLayout(scrollScreen, BoxLayout.Y_AXIS));

        /* add back button */
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(thirdScreen);

                ancestorFrame.updateFrame("MENU");
            }
        });
        thirdScreen.add(backButton);

        /* add save button */
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JSON writeToFile = (JSON) SwingUtilities.;

            }
        });
        thirdScreen.add(saveButton);

        JTextField nick0 = new JTextField("Player 1:");
        JTextField nick1 = new JTextField("Player 2:");
        JEditorPane nickChange0 = new JEditorPane();
        JEditorPane nickChange1 = new JEditorPane();

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

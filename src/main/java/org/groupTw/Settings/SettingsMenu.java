package org.groupTw.Settings;

import org.groupTw.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel {

    public SettingsMenu(){
//        this.setMaximumSize(new Dimension(AppFrame.FRAMEXSPAN, AppFrame.FRAMEYSPAN));
//        this.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN, AppFrame.FRAMEYSPAN));
//        this.setBackground(Color.cyan);

        JPanel firstScreen = new JPanel();
        JPanel secondScreen = new JPanel();
        JPanel thirdScreen = new JPanel();

        this.add(firstScreen);
        this.add(secondScreen);
        this.add(thirdScreen);

        //firstScreen.setBackground(Color.LIGHT_GRAY);
        //secondScreen.setBackground(Color.LIGHT_GRAY);
        //thirdScreen.setBackground(Color.lightGray);

        firstScreen.setMaximumSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        firstScreen.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        secondScreen.setMaximumSize(new Dimension(7 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        secondScreen.setPreferredSize(new Dimension(7 * AppFrame.FRAMEXSPAN / 9 , AppFrame.FRAMEYSPAN));

        thirdScreen.setMaximumSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        thirdScreen.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        // add scrollbar
        JPanel scrollScreen = new JPanel();
        //test.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9,2000));
        JScrollPane scrollFrame = new JScrollPane(scrollScreen);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        secondScreen.add(scrollFrame);
        scrollScreen.setLayout(new BoxLayout(scrollScreen, BoxLayout.Y_AXIS));

        // add back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame ancestorFrame = (AppFrame)SwingUtilities.getWindowAncestor(thirdScreen);

                ancestorFrame.updateFrame("MENU");
            }
        });
        thirdScreen.add(backButton);

        // add save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        scrollScreen.add(firstNick);
        nick0.setEditable(false);

        JPanel secondNick = new JPanel();
        secondNick.setBackground(Color.LIGHT_GRAY);
        secondNick.add(nick1);
        secondNick.add(nickChange1);
        scrollScreen.add(secondNick);
        nick0.setEditable(false);


    }

}

package org.groupTw.Settings;

import org.groupTw.AppFrame;
import org.groupTw.Menu;

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

        firstScreen.setBackground(Color.RED);
        secondScreen.setBackground(Color.BLUE);
        thirdScreen.setBackground(Color.YELLOW);

        firstScreen.setMaximumSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        firstScreen.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        secondScreen.setMaximumSize(new Dimension(7 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        secondScreen.setPreferredSize(new Dimension(7 * AppFrame.FRAMEXSPAN / 9 , AppFrame.FRAMEYSPAN));

        thirdScreen.setMaximumSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        thirdScreen.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));

        // add scrollbar
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9,2000));
        JScrollPane scrollFrame = new JScrollPane(test);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setPreferredSize(new Dimension( 7 * AppFrame.FRAMEXSPAN / 9, AppFrame.FRAMEYSPAN));
        secondScreen.add(scrollFrame);

        // add back button
        JButton backButton = new JButton("Krzysiu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame ancestorFrame = (AppFrame)SwingUtilities.getWindowAncestor(thirdScreen);

                ancestorFrame.updateFrame("MENU");
            }
        });
        thirdScreen.add(backButton);
    }
}

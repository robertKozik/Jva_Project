package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JPanel {
    ArrayList<JButton> buttons;
    JPanel panel;
    final static int X_BUTTON_MENU = AppFrame.FRAMEXSPAN /3;
    final static int Y_BUTTON_MENU = AppFrame.FRAMEYSPAN /10;

    public Menu()
    {
        createButtons();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, (AppFrame.FRAMEXSPAN - X_BUTTON_MENU) / 2, (AppFrame.FRAMEYSPAN - (this.buttons.size() * Y_BUTTON_MENU)) / 2));
        this.setPreferredSize(new Dimension(AppFrame.FRAMEXSPAN, AppFrame.FRAMEYSPAN));
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        for (JButton btn : buttons) {
            panel.add(btn);
        }
    }

    /**
     * Creates new arrayList of JButtons, sets ActionCommands to them and sets dimensions
     */
    private void createButtons() {
        this.buttons = new ArrayList<>();
        for (String label : new String[]{"New Game", "Settings", "Create Your Map",}) {
            JButton btn = new JButton(label);
            btn.setActionCommand(label);
            btn.addActionListener(new ButtonAction());
            btn.setPreferredSize(new Dimension(X_BUTTON_MENU, Y_BUTTON_MENU));
            btn.setMaximumSize(new Dimension(X_BUTTON_MENU, Y_BUTTON_MENU));
            buttons.add(btn);
        }

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ButtonAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        quit.setPreferredSize(new Dimension(X_BUTTON_MENU, Y_BUTTON_MENU));
        quit.setMaximumSize(new Dimension(X_BUTTON_MENU, Y_BUTTON_MENU));
        buttons.add(quit);

    }

    /**
     * method sends information to ancestor frame of JPanel to change displayed JPanel,
     *
     * @param action_ -> ActionCommand from clicked JButton instance
     */
    private void sentToFrame(String action_) {
        AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(this);
        FramesEnum enu = getEnumFromString(action_);
        if (enu != null) {
            ancestorFrame.updateFrame(enu);
        }

    }

    private FramesEnum getEnumFromString(String string) {
        switch (string) {
            case "New Game":
                return FramesEnum.NEWGAME;
            case "Settings":
                return FramesEnum.SETTINGS;
            case "Create Your Map":
                return FramesEnum.CREATEYOURMAP;
        }
        return null;
    }

    private class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New Game")) {
                Player[] ply = AppFrame.getPlayersArr();
                if (ply[0].getArmy().size() != 0 && ply[1].getArmy().size() != 0) {
                    sentToFrame(e.getActionCommand());
                    return;
                }
                buttons.get(0).setBackground(Color.RED);    //buttons[0] is "NEW GAME" button
                //tested on OSX -> this is needed in order to change button color in OSX
                buttons.get(0).setOpaque(true);
                buttons.get(0).setBorderPainted(false);
                //_________________
                return;
            }
            sentToFrame(e.getActionCommand());
        }
    }
}

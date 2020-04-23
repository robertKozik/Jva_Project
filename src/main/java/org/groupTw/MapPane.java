package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class MapPane extends JButton {

    private boolean isSelected;
    private Color border = Color.gray;

    public MapPane() {

        super();

        initUI();
    }

    public MapPane(Image image) {

        super(new ImageIcon(image));

        initUI();
    }

    private void initUI() {

        isSelected = false;
        BorderFactory.createLineBorder(border);

        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if(border != Color.green) {
                    border = Color.yellow;
                    setBorder(BorderFactory.createLineBorder(border));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(border != Color.green) {
                    border = Color.gray;
                    setBorder(BorderFactory.createLineBorder(border));
                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if(border != Color.green) {
                    border = Color.green;
                    setBorder(BorderFactory.createLineBorder(border));
                } else setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }

    public void setLastButton() {

        isSelected = true;
    }

    public boolean isLastButton() {

        return isSelected;
    }
}

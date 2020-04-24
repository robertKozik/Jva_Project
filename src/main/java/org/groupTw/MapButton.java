package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class MapButton extends JPanel {

    private boolean isSelected;
    private Color border = Color.gray;

    public MapButton() {

        super();

        initUI();
    }

    /*public MapButton(Image image) {

        super(new ImageIcon(image));

        initUI();
    }*/

    private void initUI() {
        final int n = 2;
        isSelected = false;
        BorderFactory.createLineBorder(border, n);

        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if(border != Color.red) {
                    border = Color.yellow;
                    setBorder(BorderFactory.createLineBorder(border,n));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(border != Color.RED) {
                    border = Color.gray;
                    setBorder(BorderFactory.createLineBorder(border));
                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if(border != Color.red) {
                    border = Color.red;
                } else {
                    border = Color.gray;
                }
                setBorder(BorderFactory.createLineBorder(border,n));
            }
        });
    }

    public void setSelected() {

        isSelected = true;
    }

    public boolean isSelected() {

        return isSelected;
    }

    public void setBorder(Color border) {
        this.border = border;
        this.setBorder(BorderFactory.createLineBorder(border));
    }
    public Color getTileBorder(){
        return border;
    }
}

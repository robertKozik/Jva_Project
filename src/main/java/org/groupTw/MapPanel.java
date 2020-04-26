package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


class MapPanel extends JPanel {

    private boolean isSelected;
    private Color border = Color.gray;
    private Entity entity_on_tile;

    public MapPanel() {

        super();
        entity_on_tile = null;

        initUI();
    }

    /*public MapPanel(Image image) {

        super(new ImageIcon(image));

        initUI();
    }*/

    public MapPanel(Entity entity_){
        super();
        this.entity_on_tile = entity_;
        initUI();
    }


    private void initUI() {
        final int n = 2;
        isSelected = false;
        BorderFactory.createLineBorder(border, n);

        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if(border != Color.red) {
                    if(entity_on_tile != null)border = Color.green;
                    else border = Color.yellow;
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
                if(border != Color.red && entity_on_tile != null) {
                    border = Color.red;
                } else if(entity_on_tile != null) {
                    border = Color.green;
                }else border = Color.yellow;
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

    public void setEntity_on_tile(Entity entity_on_tile) {
        this.entity_on_tile = entity_on_tile;
    }
    public Entity getEntity_on_tile(){
        return this.entity_on_tile;
    }
}

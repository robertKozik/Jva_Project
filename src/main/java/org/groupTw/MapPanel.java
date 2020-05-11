package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Color.gray;


class MapPanel extends JPanel {

    private Color border = Color.BLACK;
    protected Entity entity_on_tile;
    protected Player owner;
    final int n = 2;
    public MapPanel() {

        super();
        entity_on_tile = null;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        BorderFactory.createLineBorder(border, n);

        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if(border == Color.BLACK ) {
                    border = Color.GREEN;
                    setBorder(border,n);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(border == Color.GREEN){
                    border = Color.BLACK;
                    setBorder(border,n);
                }
            }


           /* @Override
            public void mouseReleased(MouseEvent e) {
                if(border != Color.red && entity_on_tile != null) {
                    border = Color.red;
                } else if(entity_on_tile != null) {
                    border = Color.green;
                }else border = Color.yellow;
                setBorder(BorderFactory.createLineBorder(border,n));
            }*/
        });
    }


    public void setBorder(Color border_, int width_) {
        this.border = border_;
        this.setBorder(BorderFactory.createLineBorder(border,width_));
    }
    public Color getTileBorder(){
        return border;
    }
    public Player getOwner(){return this.owner;}
    public void setOwner(Player owner_){
        this.owner = owner_;
    }
    public void setEntity_on_tile(Entity entity_on_tile) {
        this.entity_on_tile = entity_on_tile;
    }
    public Entity getEntity_on_tile(){
        return this.entity_on_tile;
    }

    public boolean isOccupied(){
        return this.entity_on_tile != null;
    }
    public boolean equals(MapPanel obj) {
        Point point1 = (Point)this.getClientProperty("Position");
        Point point2 = (Point)obj.getClientProperty("Position");
        return super.equals(obj);
    }
}

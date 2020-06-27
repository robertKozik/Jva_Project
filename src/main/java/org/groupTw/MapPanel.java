package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;


class MapPanel extends JPanel {

    private Color border = Color.BLACK;
    protected Entity entity_on_tile;
    protected Player owner;
    private Image bgImage;

    public MapPanel() {

        super();
        entity_on_tile = null;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        try{
            InputStream path = this.getClass().getResourceAsStream("/grass.png");
            this.bgImage = ImageIO.read(path);
        }catch(Exception e){
            e.printStackTrace();
        }
        initUI();
    }

    public MapPanel(Entity entity_) {
        super();
        this.entity_on_tile = entity_;
        try{
            this.bgImage = ImageIO.read(this.getClass().getResourceAsStream("/grass.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        initUI();
    }


    private void initUI() {
        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if (border == Color.BLACK) {
                    border = Color.GREEN;
                    setBorder(border, 1);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (border == Color.GREEN) {
                    border = Color.BLACK;
                    setBorder(border, 0);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = bgImage.getScaledInstance(GameLayout.MAPDIM/AppFrame.MAPSIZE,GameLayout.MAPDIM/AppFrame.MAPSIZE, Image.SCALE_FAST);
        g.drawImage(img, 0, 0, null);
    }

    public void setBorder(Color border_, int width_) {
        this.border = border_;
        this.setBorder(BorderFactory.createLineBorder(border, width_));
    }

    public Color getTileBorder() {
        return border;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner_) {
        this.owner = owner_;
    }

    public void setEntity_on_tile(Entity entity_on_tile) {
        this.entity_on_tile = entity_on_tile;
    }

    public Entity getEntity_on_tile() {
        return this.entity_on_tile;
    }

    public boolean isOccupied() {
        return this.entity_on_tile != null;
    }

    public boolean equals(MapPanel obj) {
        Point point1 = (Point) this.getClientProperty("Position");
        Point point2 = (Point) obj.getClientProperty("Position");
        return point1.equals(point2);
    }


}

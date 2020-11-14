package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
class MapPanel extends JPanel {
    /**
     * Contains, if there is any, entity which is placed on this particular MapPanel
     */
    protected Entity entity_on_tile;
    /**
     * Contains relation to Player who have unit on this MapPanel
     */
    protected Player owner;
    /**
     * Border of JPanel on map.
     */
    private Color border = Color.BLACK;
    /**
     * Background image to paint
     */
    private Image bgImage;

    /**
     * Creates MapPanel without background.
     */
    public MapPanel() {

        super();
        entity_on_tile = null;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        bgImage = null;
        initUI();
    }

    /**
     * Creates MapPanel with background. Loads image grom bgName_ path
     * @param bgName_ path to the image which will be paint as background
     */
    public MapPanel(String bgName_) {
        super();
        this.entity_on_tile = null;
        try {
            this.bgImage = ImageIO.read(this.getClass().getResourceAsStream("/" + bgName_));
        } catch (Exception e) {
            e.printStackTrace();
        }
        initUI();
    }

    /**
     * Method adds listener to MapPanel object.
     */
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
        if (bgImage != null) {
            Image img = bgImage.getScaledInstance(GameLayout.MAPDIM / AppFrame.MAPSIZE, GameLayout.MAPDIM / AppFrame.MAPSIZE, Image.SCALE_FAST);
            g.drawImage(img, 0, 0, null);
        }
    }

    //Getters and Setters ________________________________________________
    public void setBorder(Color border_, int width_) {
        this.border = border_;
        this.setBorder(BorderFactory.createLineBorder(border, width_));
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

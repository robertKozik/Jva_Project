package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
podstawowy obiekt mapy
 */
class MapPanel extends JPanel {

    private boolean isSelected;
    private Color border = Color.gray;
    protected Entity entity_on_tile;
    final int n = 2;
    public MapPanel() {

<<<<<<< Updated upstream
        super();
        entity_on_tile = null;
=======
        super(); // wywoałnie konstruktowa podstawowego obiektu JPanel
        entity_on_tile = null; // początkowo nie mamy jednostki na polu
        this.setLayout(new FlowLayout(FlowLayout.CENTER)); // ustawienie layoutu żeby jednostka wyświetlała się poprawnie
>>>>>>> Stashed changes

        initUI();
    }

    /*public MapPanel(Image image) {

        super(new ImageIcon(image));

        initUI();
    }*/
    // konstruktor tworzący pole mapy już z przypisaną jednostką
    public MapPanel(Entity entity_){
        super();
        this.entity_on_tile = entity_;
        initUI();
    }


    private void initUI() {
        BorderFactory.createLineBorder(border, n);//tworzenie ramki

<<<<<<< Updated upstream
        isSelected = false;
        BorderFactory.createLineBorder(border, n);

        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseEntered(MouseEvent e) {
                if(border != Color.red) {
                    if(entity_on_tile != null)border = Color.green;
                    else border = Color.yellow;
                    setBorder(BorderFactory.createLineBorder(border,n));
=======
        addMouseListener(new MouseAdapter() {   // dodajemy obiekt nasłuchujący odpowiedzialny za podświetlenie pola nad którym znajduje się kursor


            @Override
            public void mouseEntered(MouseEvent e) {    //kursor pojawia się nad polem, zmiana jego ramki na zieloną
                if(border == Color.BLACK ) {
                    border = Color.GREEN;
                    setBorder(border,n);
>>>>>>> Stashed changes
                }
            }

            @Override
<<<<<<< Updated upstream
            public void mouseExited(MouseEvent e) {
                if(border != Color.RED) {
                    border = Color.gray;
                    setBorder(BorderFactory.createLineBorder(border));
=======
            public void mouseExited(MouseEvent e) { //kursor oddala się od pola, zmieniamy jego ramkę na czarną
                if(border == Color.GREEN){
                    border = Color.BLACK;
                    setBorder(border,n);
>>>>>>> Stashed changes
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

    public void setBorder(Color border_, int width_) {  // metoda zmieniająca kolor ramki na podany jao argument
        this.border = border_;
        this.setBorder(BorderFactory.createLineBorder(border,width_));
    }
    //Gettery i Settery
    public Color getTileBorder(){
        return border;
    }

    public void setEntity_on_tile(Entity entity_on_tile) {
        this.entity_on_tile = entity_on_tile;
    }
    public Entity getEntity_on_tile(){
        return this.entity_on_tile;
    }

    public boolean equals(MapPanel obj) {
        Point point1 = (Point)this.getClientProperty("Position");
        Point point2 = (Point)obj.getClientProperty("Position");
        return super.equals(obj);
    }
}

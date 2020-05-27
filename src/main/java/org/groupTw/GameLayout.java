package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//Można zmienić private point na int x, int y;
// menu po lewej stronie? Gra się włącza po prawej
// pierwsza wersja, 2 jednostki, brak rozróżnienia na pola, brak budynków
//JLabel musi być center, ale jak to zrobić
public class GameLayout extends JPanel {
    private MapPanel[][] mapTiles;
    private JPanel map;
    private iLogic logic;

    public GameLayout(iLogic logic_) {
        this.logic = logic_;
        this.map = new JPanel();
        mapTiles = new MapPanel[AppFrame.MAPSIZE][AppFrame.MAPSIZE];
        initLayout();

    }

    private void initLayout() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
        map.setLayout(new GridLayout(AppFrame.MAPSIZE, AppFrame.MAPSIZE, 0, 0));

        //init empty map
        for (int i = 0; i < AppFrame.MAPSIZE; i++) {
            for (int j = 0; j < AppFrame.MAPSIZE; j++) {
                MapPanel btn = new MapPanel();
                btn.addMouseListener(new ClickAction());
                mapTiles[i][j] = btn;
                btn.putClientProperty("Position", new Point(i, j));
            }
        }

        //temporary method of making troops
        EntityFactory factory = new EntityFactory();

        logic.getPlayersArr()[0].getArmy().getTroops().add(factory.addEntity("warrior", new Point(0, 0)));
        logic.getPlayersArr()[0].getArmy().getTroops().add(factory.addEntity("warrior", new Point(0, 1)));
        logic.getPlayersArr()[0].getArmy().getTroops().add(factory.addEntity("archer", new Point(0, 2)));
        logic.getPlayersArr()[1].getArmy().getTroops().add(factory.addEntity("warrior", new Point(7, 5)));
        logic.getPlayersArr()[1].getArmy().getTroops().add(factory.addEntity("warrior", new Point(7, 6)));
        logic.getPlayersArr()[1].getArmy().getTroops().add(factory.addEntity("warrior", new Point(7, 7)));

        //assign troops to their tiles
        for( Player ply : logic.getPlayersArr()){
            for(Entity troop : ply.getArmy().getTroops()){
                Point position = troop.getPosition();
                mapTiles[(int)position.getX()][(int)position.getY()].setEntity_on_tile(troop);
            }
        }
        repaintMap();
        map.setPreferredSize(new Dimension(400, 400));
        map.setMaximumSize(new Dimension(400, 400));
        this.add(map);
        setVisible(true);


    }

    /**
     * Click Action Class is design in order to get any click on a MapTile,
     * then it decodes MapTile position property and next sends it to GameLogic class object
     * According to current state of "selected" object it runs diffrent method of GameLogic class,
     * if no tile selected, method tile not selected, if anyone selected, method tile selected is called
     * GameLogic class process the rest of game states
     */
    private class ClickAction extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            MapPanel panel = (MapPanel) e.getSource();
            Point point = (Point) panel.getClientProperty("Position"); //decode tile position
            logic.action(panel, mapTiles);

            repaintMap();
        }
    }


    private void repaintMap() {

        for(int i = 0; i<AppFrame.MAPSIZE; i++){
            for(int j = 0; j<AppFrame.MAPSIZE; j++){
                mapTiles[i][j].removeAll();
                map.add(mapTiles[i][j]);
                if(mapTiles[i][j].isOccupied()){
                    mapTiles[i][j].add(new JLabel(mapTiles[i][j].getEntity_on_tile().getPicLabel()));
                }
            }
        }
    }

}

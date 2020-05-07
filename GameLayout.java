package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//Można zmienić private point na int x, int y;
// tworzenie armii w nowej klasie ?
// ogarnięcie ataków, schematów atakowania
// menu po lewej stronie? Gra się włącza po prawej
// pierwsza wersja, 2 jednostki, brak rozróżnienia na pola, brak budynków
// przeniesienie pól do klasy wyżej ?
//JLabel musi być center, ale jak to zrobić
public class GameLayout extends JPanel
{
    final static int MapSize = 8;
    private MapPanel[][] mapTiles;
    private Player[] playersArr;
    private JPanel map;
    private GameLogic logic;
    private MapPanel selected;
    private Army currentArmy;

    public GameLayout(Player[] playersArr_, GameLogic logic_){
        this.playersArr = playersArr_;
        this.logic = logic_;
        this.map = new JPanel();
        this.selected = null;
        mapTiles = new MapPanel[MapSize][MapSize];
        initLayout();

    }

    public GameLayout() {
        map = new JPanel();
        mapTiles = new MapPanel[MapSize][MapSize];
        playersArr = new Player[2];
        logic = new GameLogic();
        selected = null;
        initLayout();
    }

    private void initLayout(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 60,50));
        map.setLayout(new GridLayout(MapSize,MapSize,0 ,0));



        for(int i = 0; i<MapSize; i++) {
            for (int j = 0; j < MapSize; j++) {
                MapPanel btn = new MapPanel();
                btn.addMouseListener(new ClickAction());
                mapTiles[i][j]=btn;
                if(i%2 == j%2) btn.setBackground(Color.GRAY);
                    else btn.setBackground(Color.white);
                btn.putClientProperty("Position", new Point(i,j));
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                map.add(btn);
            }
        }

        EntityFactory factory = new EntityFactory();

        playersArr[0].getArmy().getTroops().add(factory.addEntity("warrior", new Point(0,0)));
        playersArr[0].getArmy().getTroops().add(factory.addEntity("warrior", new Point(0,1)));
        playersArr[0].getArmy().getTroops().add(factory.addEntity("archer",new Point(0,2)));
        playersArr[1].getArmy().getTroops().add(factory.addEntity("warrior",new Point(7,5)));
        playersArr[1].getArmy().getTroops().add(factory.addEntity("warrior",new Point(7,6)));
        playersArr[1].getArmy().getTroops().add(factory.addEntity("warrior",new Point(7,7)));

        paintArmy();

       
        map.setPreferredSize(new Dimension(400,400));
        map.setMaximumSize(new Dimension(400,400));
        this.add(map);




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
            currentArmy = playersArr[logic.getRoundCounter()%2].getArmy();
            MapPanel panel = (MapPanel) e.getSource();
            Point point = (Point) panel.getClientProperty("Position"); //decode tile position
                    if(selected == null && currentArmy.getTroops().contains(panel.getEntity_on_tile())){
                        System.out.println(1);
                        panel.setBorder(Color.RED,2);
                        logic.tileNotSelected(panel, mapTiles);
                        selected = panel;
                        //paintArmy();
                    }
                    else if ( selected != null && !(point.equals((Point)selected.getClientProperty("Position")) && panel.getEntity_on_tile() == null)){
                        System.out.println(2);
                        logic.tileSelected(panel,selected,mapTiles);
                        selected.setBorder(Color.BLACK,1);
                        selected = null;

                        //paintArmy();

                    }
                    else {
                        try {
                            if (selected.getTileBorder() != Color.BLACK) selected.setBorder(Color.BLACK, 1);
                        } catch( NullPointerException exc){

                        }
                        selected = null;
                        System.out.println(3);
                        logic.clearArrAttacks();
                        logic.clearArrMoves();
                        paintArmy();
                    }
            System.out.println("Tura:"+ logic.getRoundCounter());
            paintArmy();

        }
    }


    /*
    do optymalizacji !
    O(n^2) :/
    */
    private void paintArmy() {

        for (Player ply : this.playersArr) {
            for (Entity ent : ply.getArmy().getTroops()) {
                Point position = ent.getPosition();
                for (int i = 0; i < MapSize; i++) {
                    for (int j = 0; j < MapSize; j++) {
                        if (!(mapTiles[i][j] == selected || logic.getPossibleAttacks().contains(mapTiles[i][j]) || logic.getPossibleMoves().contains(mapTiles[i][j])))
                            mapTiles[i][j].setBorder(Color.BLACK, 2);
                        Point tle = (Point) mapTiles[i][j].getClientProperty("Position");
                        if (tle.equals(position) && mapTiles[i][j].getEntity_on_tile() == null) {
                            mapTiles[i][j].add(new JLabel(ent.getPicLabel()));
                            mapTiles[i][j].setEntity_on_tile(ent);
                            break;
                        }
                    }
                }
            }
        }
    }


        private MapPanel getTileFromPoint (Point point_){
            for (int i = 0; i < MapSize; i++) {
                for (int j = 0; j < MapSize; j++) {
                    Point tilePosition = (Point) mapTiles[i][j].getClientProperty("Position");
                    if (tilePosition.equals(point_)) return mapTiles[i][j];
                }
            }
            return null;
        }



    /*
    Getters and Setters
     */

    public static int getMapSize() {
        return MapSize;
    }

    public MapPanel[][] getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(MapPanel[][] mapTiles) {
        this.mapTiles = mapTiles;
    }

    public Player[] getPlayersArr() {
        return playersArr;
    }

    public void setPlayersArr(Player[] playersArr) {
        this.playersArr = playersArr;
    }

    public MapPanel getSelected() {
        return selected;
    }

    public void setSelected(MapPanel selected) {
        this.selected = selected;
    }
}

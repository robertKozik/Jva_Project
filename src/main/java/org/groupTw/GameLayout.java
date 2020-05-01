package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameLayout extends JPanel
{
    final protected int MapSize = 8;
    private ArrayList<MapPanel> mapTiles;
    private ArrayList<Entity> player1_Army;
    private ArrayList<Entity> player2_Army;
    private JPanel map;
    private GameLogic logic;
    private MapPanel selected;


    public GameLayout() {
        map = new JPanel();
        mapTiles = new ArrayList<>();
        player1_Army = new ArrayList<>();
        logic = new GameLogic();
        selected = null;
        initLayout();
    }

    private void initLayout(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 60,50));
        map.setLayout(new GridLayout(8,8,0 ,0));



        for(int i = 0; i<MapSize; i++) {
            for (int j = 0; j < MapSize; j++) {
                MapPanel btn = new MapPanel();
                btn.addMouseListener(new ClickAction());
                mapTiles.add(btn);
                if(i%2 == j%2) btn.setBackground(Color.black);
                    else btn.setBackground(Color.white);
                btn.putClientProperty("Position", new Point(i,j));
                btn.setBorder(BorderFactory.createLineBorder(Color.gray));
                map.add(btn);
            }
        }

        EntityFactory factory = new EntityFactory();

        player1_Army.add(factory.addEntity("warrior", new Point(0,5)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,1)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,2)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,3)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,4)));

        paintArmy();

       
        map.setPreferredSize(new Dimension(400,400));
        map.setMaximumSize(new Dimension(400,400));
        this.add(map);




    }

    /**
     * Click Action Class is design in order to get any click on a MapTile,
     * then it decodes MapTile position property and next sends it to GameLogic class object
     */
    private class ClickAction extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            MapPanel panel = (MapPanel) e.getSource();
            Point point = (Point) panel.getClientProperty("Position"); //decode tile position
                    if(selected == null && panel.getEntity_on_tile() != null){
                        System.out.println(1);
                        logic.tileNotSelected(panel, mapTiles);
                        selected = panel;
                        //paintArmy();
                    }
                    else if ( selected != null && !(point.equals((Point)selected.getClientProperty("Position")))){
                        System.out.println(2);
                        logic.tileSelected(panel,selected,mapTiles);
                        selected.setBorder(Color.GRAY,1);
                        selected = null;

                        //paintArmy();

                    }
                    else {
                        selected = null;
                        System.out.println(3);
                        logic.getPossibleMoves().clear();
                        logic.getPossibleAttacks().clear();
                        paintArmy();
                    }

            paintArmy();//nwm jaki chuj ale z dwoma działa xD

        }
    }

    private void refactorArmy( MapPanel panel_){
        panel_.setEntity_on_tile(null);
        panel_.removeAll();
        paintArmy();

    }

    /*
    do optymalizacji !
    O(n^2) :/
    */
    private void paintArmy(){
        for(Entity ent : player1_Army){
            Point position = ent.getPosition();
            for(MapPanel tile : mapTiles){
                if(!(tile == selected || logic.getPossibleAttacks().contains(tile) || logic.getPossibleMoves().contains(tile) ))tile.setBorder(Color.GRAY,1);
                Point tle = (Point)tile.getClientProperty("Position");
                if(tle.equals(position) && tile.getEntity_on_tile() == null) {
                    tile.add(new JLabel(ent.getPicLabel()));
                    tile.setEntity_on_tile(ent);
                    break;
                }
            }
        }
    }

    public MapPanel getTileFromPoint(Point point_){
        for(MapPanel tile : mapTiles){
            Point tilePosition = (Point)tile.getClientProperty("Position");
            if(tilePosition.equals(point_))return tile;
        }
        return null;
    }



    /*
    Getters and Setters
     */

    public int getMapSize() {
        return MapSize;
    }

    public ArrayList<MapPanel> getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(ArrayList<MapPanel> mapTiles) {
        this.mapTiles = mapTiles;
    }

    public ArrayList<Entity> getPlayer1_Army() {
        return player1_Army;
    }

    public void setPlayer1_Army(ArrayList<Entity> player1_Army) {
        this.player1_Army = player1_Army;
    }

    public ArrayList<Entity> getPlayer2_Army() {
        return player2_Army;
    }

    public void setPlayer2_Army(ArrayList<Entity> player2_Army) {
        this.player2_Army = player2_Army;
    }

    public MapPanel getSelected() {
        return selected;
    }

    public void setSelected(MapPanel selected) {
        this.selected = selected;
    }
}

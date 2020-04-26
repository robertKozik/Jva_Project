package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class MainLayout extends JFrame
{
    static private int MapSize = 8;
    ArrayList<MapPanel> mapTiles;
    ArrayList<Entity> player1_Army;
    ArrayList<Entity> player2_Army;
    private JPanel panel_1;
    private JPanel map;


    public MainLayout() {
        init();
    }
    private void init(){
        this.setSize(1000,520);
        //divide on settings, and game map
        panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(1,2,0,0));
        this.add(panel_1);
        //settings panel
        JPanel left = new JPanel();
        //JLabel label = new JLabel();
        //label.setText("XXX");
        //left.add(label);
        //left.setBackground(Color.black);
        panel_1.add(left);
        //game map
        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout(FlowLayout.CENTER, 60,50));
        //flow.setBackground(Color.blue);
        panel_1.add(flow);

        map = new JPanel();
        map.setLayout(new GridLayout(8,8,0 ,0));
        mapTiles = new ArrayList<>();
        player1_Army = new ArrayList<>();


        for(int i = 0; i<MapSize; i++) {
            for (int j = 0; j < MapSize; j++) {
                MapPanel btn = new MapPanel();
                mapTiles.add(btn);
                if(i%2 == j%2) btn.setBackground(Color.black);
                    else btn.setBackground(Color.white);
                btn.putClientProperty("Position", new Point(i,j));
                btn.setBorder(BorderFactory.createLineBorder(Color.gray));
                map.add(btn);
            }
        }

        Warrior war = new Warrior(new Point(1,1),10,10,10,true,true);
        player1_Army.add(war);
        /*
        do optymalizacji !
         */
        for(Entity ent : player1_Army){
            Point position = ent.position;
            System.out.println(mapTiles.size());
            for(MapPanel tile : mapTiles){
                Point tle = (Point)tile.getClientProperty("Position");
                if(tle.equals(position)) {
                    tile.add(new JLabel(war.picLabel));
                    tile.setEntity_on_tile(ent);

                }
            }
        }

        /*for(MapPanel btn : mapTiles){
            map.add(btn);
        }*/


        map.setPreferredSize(new Dimension(400,400));
        map.setMaximumSize(new Dimension(400,400));
        flow.add(map);



        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

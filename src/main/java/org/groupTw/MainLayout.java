package org.groupTw;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class MainLayout extends JFrame
{
    List<MapButton> buttons;
    private JPanel panel_1;
    private JPanel map;
    static private int MapSize = 8;

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
        left.setBackground(Color.black);
        panel_1.add(left);
        //game map
        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout(FlowLayout.CENTER, 60,50));
        //flow.setBackground(Color.blue);
        panel_1.add(flow);

        map = new JPanel();
        map.setLayout(new GridLayout(8,8,0 ,0));

        for(int i = 0; i<MapSize; i++) {
            for (int j = 0; j < MapSize; j++) {
                MapButton btn = new MapButton();
                if(i%2 == j%2) btn.setBackground(Color.black);
                else btn.setBackground(Color.white);
                btn.putClientProperty("Position", new Point(i,j));
                //btn.add(new JPanel());
                map.add(btn);
                btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        }

        map.setPreferredSize(new Dimension(400,400));
        map.setMaximumSize(new Dimension(400,400));
        flow.add(map);



        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

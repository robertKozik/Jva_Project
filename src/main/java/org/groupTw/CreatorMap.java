package org.groupTw;

import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.EntityFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreatorMap extends JPanel {
    private GameLayout mapLayout;
    private JPanel chooseMenu;
    static private ArrayList<Entity> prototypes = new ArrayList<>();
    static private int entityToPlace = -1;
    public CreatorMap(GameLayout mapLayout) {
        this.mapLayout = mapLayout;
        this.chooseMenu = new JPanel(new GridLayout(1,4));
        initView();
    }

    private void initView() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(mapLayout);

        //creating prototypes
        EntityFactory factory = new EntityFactory();
        CreatorMap.prototypes.add(factory.addEntity("archer"));
        CreatorMap.prototypes.add(factory.addEntity("warrior"));
        CreatorMap.prototypes.add(factory.addEntity("archer tower"));

        CreatorMap.prototypes.forEach(unit ->{
            MapPanel unitPanel = new MapPanel();
            unitPanel.setEntity_on_tile(unit);
            unitPanel.setBorder(Color.BLACK,2);
            unitPanel.add( new JLabel(unit.getPicLabel()) );
            unitPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    MapPanel source = (MapPanel)e.getSource();
                    Entity entityClicked = source.getEntity_on_tile();
                    for(int i = 0; i < CreatorMap.prototypes.size()-1; i++){
                        if(CreatorMap.prototypes.get(i).equals(entityClicked)){
                            CreatorMap.entityToPlace = i;
                            break;
                        }
                    }
                    System.out.println("Entity to place:"+ CreatorMap.prototypes.get(entityToPlace).toString());
                }

            });
            chooseMenu.add(unitPanel);
        });
        this.add(chooseMenu);

    }

    public static int getEntityToPlace() {
        return entityToPlace;
    }


}

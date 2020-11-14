package org.groupTw;

import org.groupTw.MapEnitites.Entity;
import org.groupTw.MapEnitites.EntityFactory;
import org.groupTw.MapEnitites.UnitEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CreatorMap extends JPanel {
    private GameLayout mapLayout;
    private JPanel chooseMenuPlayer1;
    private JPanel chooseMenuPlayer2;
    static protected ArrayList<Entity> prototypes = new ArrayList<>();
    static protected int entityToPlace = -1;
    static protected MapPanel selected = null;
    private ButtonGroup btnGroup;
    private JPanel buttonPanel;

    public CreatorMap(GameLayout mapLayout) {
        this.mapLayout = mapLayout;
        this.chooseMenuPlayer1 = new JPanel(new GridLayout());
        this.chooseMenuPlayer2 = new JPanel(new GridLayout());
        this.buttonPanel = new JPanel();
        this.btnGroup = new ButtonGroup();
        chooseMenuPlayer1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        chooseMenuPlayer2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        initView();

    }

    private void initView() {
        CreatorMap.entityToPlace = -1;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JPanel secondaryLayout = new JPanel();
        secondaryLayout.setLayout(new BoxLayout(secondaryLayout, BoxLayout.X_AXIS));
        this.add(secondaryLayout);
        addPlayerChooseButtons();
        secondaryLayout.add(buttonPanel);
        JButton returnButton = new JButton("RETURN");
        returnButton.addActionListener(e -> sentToFrame(FramesEnum.ENDCREATE));
        secondaryLayout.add(returnButton);

        secondaryLayout.add(mapLayout);

        //creating prototypes
        if (CreatorMap.prototypes.size() == 0)
            createPrototypes();

        //place prototypes within chooseMenu
        createChooseMenu();

        this.add(chooseMenuPlayer1);
        repaint();


    }

    private void createChooseMenu() {
        AtomicInteger i = new AtomicInteger();
        CreatorMap.prototypes.forEach(unit -> {
            MapPanel unitPanel = new MapPanel();
            unitPanel.setEntity_on_tile(unit);
            unitPanel.add(new JLabel(unit.getPicLabel()));
            unitPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (selected != null )
                        selected.setBorder(null);
                    selected = (MapPanel) e.getSource();
                    Entity entityClicked = selected.getEntity_on_tile();
                    for (int k = 0; k < CreatorMap.prototypes.size(); k++)//search grid to get prototype index
                    {
                        if (CreatorMap.prototypes.get(k).equals(entityClicked)) {
                            CreatorMap.entityToPlace = k;
                            System.out.println(entityClicked.toString());
                            break;
                        }
                    }
                    //Bordering chosen entity to place for one next click
                    selected.setBorder(Color.RED, 2);
                    System.out.println("Entity to place:" + CreatorMap.prototypes.get(entityToPlace).toString());
                }

            });
            if (i.get() < CreatorMap.prototypes.size() / 2)
                chooseMenuPlayer1.add(unitPanel);
            else
                chooseMenuPlayer2.add(unitPanel);
            i.getAndIncrement();
        });
    }

    private void createPrototypes() {
        EntityFactory factory = new EntityFactory();
        try {
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.ARCHER, UnitEnum.BLUE));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.WARRIOR, UnitEnum.BLUE));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.CATAPULT, UnitEnum.BLUE));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.MERCENARY, UnitEnum.BLUE));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.TOWER, UnitEnum.BLUE));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.SIEGE, UnitEnum.BLUE));

            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.ARCHER, UnitEnum.RED));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.WARRIOR, UnitEnum.RED));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.CATAPULT, UnitEnum.RED));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.MERCENARY, UnitEnum.RED));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.TOWER, UnitEnum.RED));
            CreatorMap.prototypes.add(factory.addEntity(UnitEnum.SIEGE, UnitEnum.RED));
        } catch (NullPointerException exc) {
            exc.printStackTrace();
        }


    }

    private void addPlayerChooseButtons() {
        ActionListener listener = event -> {
            JRadioButton btn = (JRadioButton) event.getSource();
            switch (btn.getName()) {
                case "Player 1":
                    this.remove(chooseMenuPlayer2);
                    this.add(chooseMenuPlayer1);
                    revalidate();
                    repaint();
                    System.out.println("Player1");
                    break;
                case "Player 2":
                    this.remove(chooseMenuPlayer1);
                    this.add(chooseMenuPlayer2);
                    revalidate();
                    repaint();
                    System.out.println("Player2");
                    break;
            }
        };

        for (String str : new String[]{"Player 1", "Player 2"}) {
            JRadioButton btn = new JRadioButton(str, str.equals("Player 1"));
            btn.setName(str);
            btn.addActionListener(listener);
            btnGroup.add(btn);
            buttonPanel.add(btn);
        }

    }

    private void sentToFrame(FramesEnum action_) {
        AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(this);
        ancestorFrame.updateFrame(action_);

    }

}

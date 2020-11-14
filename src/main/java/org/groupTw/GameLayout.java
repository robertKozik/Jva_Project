package org.groupTw;

import org.groupTw.MapEnitites.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main Layout of game. Consists of map of game and statistics tab.
 */
public class GameLayout extends JPanel {
    /**
     * width of map display in px.
     */
    final public static int MAPDIM = 600;
    /**
     * Array of MapTiles. It's representation of main view of the game.
     */
    public MapPanel[][] mapTiles;
    /**
     * Main layout of JPanel, setted in initLayout() method.
     */
    private JTabbedPane mainLayout;
    /**
     * child Jpanel of Gamelayout which contains statistics of alive units.
     */
    private JPanel statistics;
    /**
     * JPanl in which mapTiles will be displayed.
     */
    private JPanel map;
    /**
     * Object of iLogic which will be responible for handling user input.
     */
    private iLogic logic;

    /**
     * Creates instance of GameLayout, creates instances of fields
     *
     * @param logic_ instance of class that implements iLogic interface, it handles user input
     */
    public GameLayout(iLogic logic_) {
        this.mainLayout = new JTabbedPane();
        this.logic = logic_;
        this.statistics = new JPanel();
        this.map = new JPanel();
        mapTiles = new MapPanel[AppFrame.MAPSIZE][AppFrame.MAPSIZE];
        initLayout();

    }

    /**
     * inits sublayouts, inits mapTiles with empty mapTile instances, sets Size of Frame
     */
    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.add(mainLayout);
        map.setLayout(new GridLayout(AppFrame.MAPSIZE, AppFrame.MAPSIZE, 0, 0));


        //init empty map
        for (int i = 0; i < AppFrame.MAPSIZE; i++) {
            for (int j = 0; j < AppFrame.MAPSIZE; j++) {
                MapPanel btn = new MapPanel("grass.png");
                btn.addMouseListener(new ClickAction());
                mapTiles[i][j] = btn;
                btn.putClientProperty("Position", new Point(i, j));
                map.add(mapTiles[i][j]);
            }
        }

        placeEntitiesOnMap();


        map.setPreferredSize(new Dimension(MAPDIM, MAPDIM));
        map.setMaximumSize(new Dimension(MAPDIM, MAPDIM));
        mainLayout.addTab("map", this.map); //show map
        createStatisticsTab();//add statistic tab
        setVisible(true);
        repaintMap();
        revalidate();
        repaint();

    }

    //assign all troops to their tiles

    /**
     * Assings all troops from players to their position on map, sets ownership of tiles
     */
    private void placeEntitiesOnMap() {

        for (Player ply : logic.getPlayersArr()) {
            for (Entity troop : ply.getArmy()) {
                Point position = troop.getPosition();
                int xPosition = (int) position.getX();
                int yPosition = (int) position.getY();

                mapTiles[xPosition][yPosition].setEntity_on_tile(troop);
                mapTiles[xPosition][yPosition].setOwner(ply);

                revalidate();
                repaint();
            }
        }
    }

    //show all units on assigned tiles

    /**
     * Paints all alive units on MapTiles
     */
    private void repaintMap() {

        for (int i = 0; i < AppFrame.MAPSIZE; i++) {
            for (int j = 0; j < AppFrame.MAPSIZE; j++) {
                MapPanel currentTile = mapTiles[i][j];
                currentTile.removeAll();
                if (currentTile.isOccupied()) {
                    currentTile.add(new JLabel(currentTile.getEntity_on_tile().getPicLabel()));
                }
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Creates layout of statistics JPanel, initializes statistics for every unit and adds them to statistics JPanel
     */
    private void createStatisticsTab() {
        mainLayout.remove(statistics);
        statistics = new JPanel();
        statistics.setLayout(new BorderLayout());

        JPanel mainStatisticsLayout = new JPanel();
        mainStatisticsLayout.setLayout(new BoxLayout(mainStatisticsLayout, BoxLayout.Y_AXIS));

        for (Player ply : logic.getPlayersArr()) {
            JPanel grid = new JPanel(new GridLayout(1, ply.getArmy().size()));
            for (Entity ent :
                    ply.getArmy()) {
                JPanel unitPanel = new JPanel();
                unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.Y_AXIS));
                JLabel pic = new JLabel(ent.getPicLabel());
                JLabel name = new JLabel(ent.toString());
                JLabel position = new JLabel("X: " + (int) ent.getPosition().getX() + " Y: " + (int) ent.getPosition().getY());
                JLabel attack = new JLabel("Attack: " + ent.getAttack());
                JLabel health = new JLabel("Health: " + ent.getCurrentHealth() + "/" + ent.getMaxHealth());

                unitPanel.add(pic);
                unitPanel.add(name);
                unitPanel.add(position);
                unitPanel.add(health);
                unitPanel.add(attack);
                grid.add(unitPanel);
            }
            mainStatisticsLayout.add(grid);
        }

        statistics.add(mainStatisticsLayout);
        mainLayout.addTab("Stats", statistics);
    }

    private void sentToFrame(FramesEnum action_) {
        AppFrame ancestorFrame = (AppFrame) SwingUtilities.getWindowAncestor(this);
        ancestorFrame.updateFrame(action_);

    }

    /**
     * Click Action Class is design in order to get any click on a MapTile,
     * then it decodes MapTile position property and next sends it to instance of iLogic interface object
     * According to current state of "selected" object it runs different method of GameLogic class,
     * if no tile selected, method tile not selected, if anyone selected, method tile selected is called.
     * GameLogic class process the rest of game states.
     */
    private class ClickAction extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            MapPanel panel = (MapPanel) e.getSource();

            logic.action(panel, mapTiles);

            if (logic instanceof GameLogic) {
                if (((GameLogic) logic).getWinner() != null) {
                    sentToFrame(FramesEnum.SCOREBOARD);
                }
            }
            repaintMap();
            createStatisticsTab();
        }
    }
}

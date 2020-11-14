package org.groupTw;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame of Application, displays other JFrames
 */
public class AppFrame extends JFrame {
    /**
     * represents number of squares, which builds map of game, map is a squere consists of MAPSIZE squares wide and
     * MAPSIZE squares high
     */
    final public static int MAPSIZE = 10;
    /**
     * Height of main window
     */
    final public static int FRAMEYSPAN = 600;
    /**
     * width of main window
     */
    final public static int FRAMEXSPAN = 800;
    /**
     * a static array which consists of Players objects
     */
    static private Player[] playersArr;

    private Menu menuFrame;
    private SettingsMenu settingsFrame;
    private GameLayout gameFrame;
    private CreatorMap creatorFrame;
    private ScoreBoard scoreboardFrame;


    /**
     * Creates application window, initiates its default features, initializes players array with generic players
     */
    public AppFrame() {
        init();
        playersArr = new Player[2];
        for (int i = 0; i < 2; i++)
            playersArr[i] = new Player();//init new players
    }

    //getters and setters
    public static Player[] getPlayersArr() {
        return playersArr;
    }

    /**
     * Initiates features of main window, its dimensions, close operation, resize, layout. Sets Menu JFrame to display
     */
    private void init() {
        menuFrame = new Menu();
        settingsFrame = new SettingsMenu();
        this.setSize(FRAMEXSPAN, FRAMEYSPAN);
        this.setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateFrame(FramesEnum.MENU);
    }

    /**
     * method changes displayed JPanel, removes currently displayed one
     *
     * @param name_ name from FramesEnum of JPanel to invoke in frame
     */
    public void updateFrame(FramesEnum name_) {

        switch (name_) {
            case MENU:
                revalidate();
                repaint();
                this.add(menuFrame);
                this.remove(settingsFrame);
                pack();
                setVisible(true);
                break;
            case NEWGAME:
                JSON json = new JSON("/nicks.txt");
                playersArr[0].setPlayerName(json.JSONReadFromFile("player1"));
                playersArr[1].setPlayerName(json.JSONReadFromFile("player2"));

                this.remove(menuFrame);
                iLogic logic = new GameLogic(playersArr);
                gameFrame = new GameLayout(logic);
                this.add(gameFrame);
                setVisible(true);
                pack();
                break;
            case SETTINGS:
                this.remove(menuFrame);
                this.add(settingsFrame);
                setVisible(true);
                pack();
                break;
            case ENDGAME:
                this.remove(scoreboardFrame);
                updateFrame(FramesEnum.MENU);
                break;
            case ENDCREATE:
                this.remove(creatorFrame);
                updateFrame(FramesEnum.MENU);
                break;
            case CREATEYOURMAP:
                this.remove(menuFrame);
                logic = new CreatorLogic(playersArr);
                creatorFrame = new CreatorMap(new GameLayout(logic));
                this.add(creatorFrame);
                setVisible(true);
                pack();
                break;
            case SCOREBOARD:
                this.remove(gameFrame);
                scoreboardFrame = new ScoreBoard();
                this.add(scoreboardFrame);
                revalidate();
                repaint();
                pack();

        }
    }

    public static void setPlayersArr(Player[] playersArr) {
        AppFrame.playersArr = playersArr;
    }
}

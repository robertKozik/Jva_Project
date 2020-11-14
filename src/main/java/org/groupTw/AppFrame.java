package org.groupTw;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    final public static int MAPSIZE = 10;
    final public static int FRAMEYSPAN = 600;
    final public static int FRAMEXSPAN = 800;
    static private Player[] playersArr;
    private Menu menu;
    private SettingsMenu settingsMenu;
    private GameLayout mainGame;
    private CreatorMap create;
    private ScoreBoard scoreBoard;


    public AppFrame() {
        menu = new Menu();
        settingsMenu = new SettingsMenu();
        mainGame = null;
        playersArr = new Player[2];
        for (int i = 0; i < 2; i++)
            playersArr[i] = new Player();//init new players
        this.setSize(FRAMEXSPAN, FRAMEYSPAN);
        this.setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateFrame(FramesEnum.MENU);
    }

    //getters and setters
    public static Player[] getPlayersArr() {
        return playersArr;
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
                this.add(menu);
                this.remove(settingsMenu);
                pack();
                setVisible(true);
                break;
            case NEWGAME:
                JSON json = new JSON("/nicks.txt");
                playersArr[0].setPlayerName(json.JSONReadFromFile("player1"));
                playersArr[1].setPlayerName(json.JSONReadFromFile("player2"));

                this.remove(menu);
                iLogic logic = new GameLogic(playersArr);
                mainGame = new GameLayout(logic);
                this.add(mainGame);
                setVisible(true);
                pack();
                break;
            case SETTINGS:
                this.remove(menu);
                this.add(settingsMenu);
                setVisible(true);
                pack();
                break;
            case ENDGAME:
                this.remove(scoreBoard);
                updateFrame(FramesEnum.MENU);
                break;
            case ENDCREATE:
                this.remove(create);
                updateFrame(FramesEnum.MENU);
                break;
            case CREATEYOURMAP:
                this.remove(menu);
                logic = new CreatorLogic(playersArr);
                create = new CreatorMap(new GameLayout(logic));
                this.add(create);
                setVisible(true);
                pack();
                break;
            case SCOREBOARD:
                this.remove(mainGame);
                scoreBoard = new ScoreBoard();
                this.add(scoreBoard);
                revalidate();
                repaint();
                pack();

        }
    }

    public static void setPlayersArr(Player[] playersArr) {
        AppFrame.playersArr = playersArr;
    }
}

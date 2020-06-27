package org.groupTw;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    final public static int MAPSIZE = 10;
    final public static int FRAMEYSPAN = 920;
    final public static int FRAMEXSPAN = 900;
    static private Player[] playersArr;
    private iLogic logic;
    private Menu menu;
    private GameLayout mainGame;
    private CreatorMap create;
    private ScoreBoard scoreBoard;


    public AppFrame () {
        menu = new Menu();
        mainGame = null;
        this.playersArr = new Player[2];
        for(int i=0; i<2; i++)
            this.playersArr[i] = new Player();//init new players
        this.setSize(FRAMEXSPAN, FRAMEYSPAN);
        this.setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateFrame("MENU");
        }


    void updateFrame(String name_){
        String upperCase = name_.toUpperCase();
        switch (upperCase)
        {
            case "MENU":
                revalidate();
                repaint();
                this.add(menu);
                pack();
                setVisible(true);
                break;
            case "NEW GAME":
                this.remove(menu);
                logic = new GameLogic(this.playersArr);
                mainGame = new GameLayout(logic);
                this.add(mainGame);
                setVisible(true);
                pack();
                break;
            case "ENDGAME":
                this.remove(scoreBoard);
                updateFrame("MENU");
                break;
            case "ENDCREATE":
                this.remove(create);
                updateFrame("MENU");
                break;
            case "CREATE YOUR MAP":
                this.remove(menu);
                logic = new CreatorLogic(playersArr);
                create = new CreatorMap(new GameLayout(logic));
                this.add(create);
                setVisible(true);
                pack();
                break;
            case "SCOREBOARD":
                this.remove(mainGame);
                scoreBoard = new ScoreBoard();
                this.add(scoreBoard);
                revalidate();
                repaint();
                pack();

        }
    }

    public static Player[] getPlayersArr() {
        return playersArr;
    }
}

package org.groupTw;

/**
 * Interface which needs to be implemented in order to handle user input from GameLayout or CreatorLayout
 */
public interface iLogic {

    /**
     * Method handles user input on the game map.
     *
     * @param tile_     selected by user MapPanel.
     * @param mapTiles_ Current state of Game.
     */
    void action(MapPanel tile_, MapPanel[][] mapTiles_);

    Player[] getPlayersArr();
}

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
        map = new JPanel(); //obiekt wyświetlający mapę
        mapTiles = new ArrayList<>();   // lista wszystkich pól mapy
        player1_Army = new ArrayList<>();   //lista jednostek gracza
        player2_Army = new ArrayList<>();      //lista jednostek gracza
        logic = new GameLogic();
        selected = null;
        initLayout();
    }

    /**
     * Tworzy całą strukturę gry
     */
    private void initLayout(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 60,50));
        map.setLayout(new GridLayout(8,8,0 ,0));


        /*
        zapoczątkowuje mapę, wypełnia każdą powstałą w GridLayout'cie komórkę nowym polem,
        dodaje do niego listener odpowidzialny za wykrycie kliknięcia, ustawia tło kafelka, dodaje do niego jego pozycję ( Point(x,y) )
         */
        for(int i = 0; i<MapSize; i++) {
            for (int j = 0; j < MapSize; j++) {
                MapPanel btn = new MapPanel();
<<<<<<< Updated upstream
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
=======
                btn.addMouseListener( new ClickAction() ); // dodanie nowego obiektu nasłuchującego
                mapTiles.add(btn);  //dodanie do listy pól
                if(i%2 == j%2) btn.setBackground(Color.GRAY); //tło
                    else btn.setBackground(Color.white);
                btn.putClientProperty("Position", new Point(i,j)); // dodanie punktu indentyfikującego kafelek
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                map.add(btn); // dodanie do wyświetlenia
            }
        }

        EntityFactory factory = new EntityFactory();    // obiekt factory pozwalający tworzyć wszystkie możliwe jednostki
        //tworzenie jednostek:
        player1_Army.add(factory.addEntity("warrior", new Point(0,0)));
        player1_Army.add(factory.addEntity("archer",new Point(0,6)));
>>>>>>> Stashed changes
        player1_Army.add(factory.addEntity("warrior",new Point(0,1)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,2)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,3)));
        player1_Army.add(factory.addEntity("warrior",new Point(0,4)));

        paintArmy();    //rysowanie jednostek na mapie

       //ustalenie wymiarów mapy:
        map.setPreferredSize(new Dimension(400,400));
        map.setMaximumSize(new Dimension(400,400));
        this.add(map); //dodanie mapy do JPanel do wyświetlenia




    }

    /**
     * Click Action Class is design in order to get any click on a MapTile,
     * then it decodes MapTile position property and next sends it to GameLogic class object
     */
    private class ClickAction extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) { //mouseRelesed może być mouseClicked lub mousePressed
            MapPanel panel = (MapPanel) e.getSource();  //ustalamy jaki kafelek został kliknięty
            Point point = (Point) panel.getClientProperty("Position"); //decode tile position
<<<<<<< Updated upstream
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
=======
                    if(selected == null && panel.getEntity_on_tile() != null){  // jeżeli zadan kafelek wcześniej nie został wybrany a znajduje się na nim jednostka to ustalamy że to pole jest już wybrane i wyświetlamy możliwe ruchy jednostki znajdującej się na nim
                        //System.out.println(1);
                        panel.setBorder(Color.RED,2); //oznaczamy wybrane pole czerwoną ramką
                        logic.tileNotSelected(panel, mapTiles); //przekazanie do obiektu gamelogic
                        selected = panel; // przypisanie pola jako wybranego aktualnie
                        //paintArmy();
                    }
                    else if ( selected != null && !(point.equals((Point)selected.getClientProperty("Position")) && panel.getEntity_on_tile() == null)){ // jeżeli pole jest wybrane wcześniej, a teraz kliknięte zostało inne pole niż wybrane, które jest puste -> następuje ruch jednostki na puste pole
                        System.out.println(2);
                        logic.tileSelected(panel,selected,mapTiles); //przekazanie do obiektu gamelogic, ruch się wykonuje
                        selected.setBorder(Color.BLACK,1); //usunięcie oznaczenia
                        selected = null; //żadne pole nie jest wybrane
>>>>>>> Stashed changes

                        //paintArmy();

                    }
<<<<<<< Updated upstream
                    else {
                        selected = null;
                        System.out.println(3);
                        logic.getPossibleMoves().clear();
                        logic.getPossibleAttacks().clear();
                        paintArmy();
                    }

            paintArmy();//nwm jaki chuj ale z dwoma działa xD
=======
                    else {//tutaj trafiamy jak na przykłąd kilikniemy drugi raz to samo pole
                        try {
                            if (selected.getTileBorder() != Color.BLACK) selected.setBorder(Color.BLACK, 1);
                        } catch( NullPointerException exc){

                        }
                        selected = null; //żadne pole nie jest już wybrane
                        System.out.println(3);
                        logic.clearArrAttacks(); //usuwamy oznaczenie możliwych ataków
                        logic.clearArrMoves();  //usuwamy oznaczenie możliwych ruchów
                        paintArmy();
                    }
            System.out.println("Tura:"+ logic.getRoundCounter());
            paintArmy(); //po każdym możliwym kliknięciu rysujemy jednostki
>>>>>>> Stashed changes

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

    funkcja rysująca jednostki
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

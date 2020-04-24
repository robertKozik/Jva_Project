package org.groupTw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Entity {
    Point position;
    ArrayList<Point> possible_attacks;
    Image image;
    boolean canAttack;
    int health;
    int attack;
    int defense;

    public Entity(){
        this.position = new Point(0,0);
        possible_attacks = new ArrayList<>();
        this.canAttack = false;
        this.health = 0;
        this.attack = 0;
        this.defense = 0;
    }
    public Entity(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_ ) throws IOException{
        this.position = position_;
        this.image = loadImage( imagePath_ );
        this.canAttack = canAttack_;
        this.health = health_;
        this.attack = attack_;
        this.defense = defense_;
    }



    private Image loadImage( String imagePath) throws IOException {
        try {
            Image bimg = ImageIO.read(new File(imagePath));
            return bimg;
        }
        catch(IOException exc){
            Logger.getLogger(Main.class.getName()).log(
                    Level.SEVERE, null, exc);
             }
        return null;
    }

    abstract void AttackPattern();

}

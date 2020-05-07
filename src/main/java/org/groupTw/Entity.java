package org.groupTw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
klasa abstrakcyjna po której dziedziczy każda jednostka
 */
abstract public class Entity {
    private Point position;
    private ArrayList<Point> possible_attacks;
    private ArrayList<Point> possible_moves;
    private boolean canAttack;
    private int health;
    private int attack;
    private int defense;
    private ImageIcon picLabel;
    //__________________wymiary obrazka jednostki
    final int imgW = 35;
    final int imgH = 35;
    //Konstruktory
    public Entity(){
        this.position = new Point(0,0);
        this.possible_attacks = new ArrayList<>();
        this.possible_moves = new ArrayList<>();
        this.canAttack = false;
        this.health = 0;
        this.attack = 0;
        this.defense = 0;
    }
    public Entity(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_ ) {
        this.possible_attacks = new ArrayList<>();
        this.possible_moves = new ArrayList<>();
        this.position = position_;
        this.canAttack = canAttack_;
        this.health = health_;
        this.attack = attack_;
        this.defense = defense_;
        picLabel = new ImageIcon(loadImage(imagePath_));
    }


    //funkcja odpowiedzialna za załadowanie obrazka jednostki
    private Image loadImage( String imagePath)  {
        try {
            Image img = ImageIO.read(new File(imagePath));
            Image bimg = img.getScaledInstance(imgW,imgH,Image.SCALE_FAST);
            return bimg;
        }
        catch(IOException exc){
            Logger.getLogger(Main.class.getName()).log(
                    Level.SEVERE, null, exc);
             }
        return null;
    }


    //funkcje abstrakcyjne które mogą zostać zaimplementowane
    abstract void AttackPattern();
    abstract void MovePattern();
    abstract void Move(Point position_);


    //gettery i settery
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public ArrayList<Point> getPossible_attacks() {
        return possible_attacks;
    }

    public void setPossible_attacks(ArrayList<Point> possible_attacks) {
        this.possible_attacks = possible_attacks;
    }

    public ArrayList<Point> getPossible_moves() {
        return possible_moves;
    }

    public void setPossible_moves(ArrayList<Point> possible_moves) {
        this.possible_moves = possible_moves;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public ImageIcon getPicLabel() {
        return picLabel;
    }

    public void setPicLabel(ImageIcon picLabel) {
        this.picLabel = picLabel;
    }

    public int getImgW() {
        return imgW;
    }

    public int getImgH() {
        return imgH;
    }
}

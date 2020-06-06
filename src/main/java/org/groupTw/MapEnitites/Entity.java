package org.groupTw.MapEnitites;

import org.groupTw.AppFrame;
import org.groupTw.GameLayout;
import org.groupTw.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Entity {
    private Point position;
    private ArrayList<Point> possible_attacks;
    private boolean canAttack;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private boolean isAlive;
    private ImageIcon picLabel;
    public final static int BLUE = 1;
    public final static int RED = 2;
    //__________________
    final int imgW = (int)(GameLayout.MAPDIM*0.7)/AppFrame.MAPSIZE;
    final int imgH = (int)(GameLayout.MAPDIM*0.7)/AppFrame.MAPSIZE;

    public Entity(String imagePath_){
        this.position = new Point(-1,-1);
        this.possible_attacks = new ArrayList<>();
        this.canAttack = false;
        this.maxHealth = 0;
        this.currentHealth = this.maxHealth;
        this.attack = 0;
        this.defense = 0;
        this.isAlive = true;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    public Entity(Point position_, String imagePath_, int health_, int attack_, int defense_, boolean canAttack_ ) {
        this.possible_attacks = new ArrayList<>();
        this.position = position_;
        this.canAttack = canAttack_;
        this.maxHealth = health_;
        this.currentHealth = this.maxHealth;
        this.attack = attack_;
        this.defense = defense_;
        this.isAlive = true;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    private Image loadImage( String imagePath)  {
        try {
            Image img = ImageIO.read(new File(imagePath));
            return img.getScaledInstance(imgW,imgH,Image.SCALE_FAST);
        }
        catch(IOException exc){
            Logger.getLogger(Main.class.getName()).log(
                    Level.SEVERE, null, exc);
             }
        return null;
    }

    abstract void AttackPattern();

    public boolean getDamage(int damage_){
        this.currentHealth -= damage_;
        return this.currentHealth > 0;
    }

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


    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return maxHealth == entity.maxHealth &&
                Objects.equals(position, entity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, maxHealth);
    }
}

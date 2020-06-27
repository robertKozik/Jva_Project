package org.groupTw.MapEnitites;

import org.apache.commons.lang3.SerializationUtils;
import org.groupTw.AppFrame;
import org.groupTw.GameLayout;
import org.groupTw.Main;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.*;

public abstract class Entity implements Cloneable, Serializable {
    private Point position;
    private ArrayList<Point> possible_attacks;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private boolean isAlive;
    private ImageIcon picLabel;
    private String color;

    //__________________
    final int imgW = (int) (GameLayout.MAPDIM * 0.7) / AppFrame.MAPSIZE;
    final int imgH = (int) (GameLayout.MAPDIM * 0.7) / AppFrame.MAPSIZE;

    public Entity(String imagePath_, int health_, int attack_, int defense_) {
        this.position = new Point(-1, -1);
        this.possible_attacks = new ArrayList<>();
        this.maxHealth = health_;
        this.currentHealth = this.maxHealth;
        this.attack = attack_;
        this.defense = defense_;
        this.isAlive = true;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    public Entity(Point position_, String imagePath_, int health_, int attack_, int defense_) {
        this.possible_attacks = new ArrayList<>();
        this.position = position_;
        this.maxHealth = health_;
        this.currentHealth = this.maxHealth;
        this.attack = attack_;
        this.defense = defense_;
        this.isAlive = true;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    public Entity clone()
            throws SerializationException {
        return (Entity) SerializationUtils.clone(this);
    }

    private Image loadImage(String imagePath) {
        try {
            Image img = ImageIO.read(this.getClass().getResourceAsStream(imagePath));
            return img.getScaledInstance(imgW, imgH, Image.SCALE_FAST);
        } catch (IOException exc) {
            System.out.println(imagePath);
        }
        return null;
    }

    abstract void AttackPattern();

    public boolean getDamage(int damage_) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return Objects.equals(position, entity.position) && color.equals(entity.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, maxHealth);
    }
}

package org.groupTw.MapEnitites;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.groupTw.AppFrame;
import org.groupTw.GameLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Entity implements Cloneable, Serializable {
    private Point position;
    private ArrayList<Point> possible_attacks;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private ImageIcon picLabel;
    private UnitEnum color;

    //__________________
    final int imgW = (int) (GameLayout.MAPDIM * 0.7) / AppFrame.MAPSIZE;
    final int imgH = (int) (GameLayout.MAPDIM * 0.7) / AppFrame.MAPSIZE;

    public Entity(String imagePath_, int health_, int attack_) {
        this.position = new Point(-1, -1);
        this.possible_attacks = new ArrayList<>();
        this.maxHealth = health_;
        this.currentHealth = this.maxHealth;
        this.attack = attack_;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    public Entity(Point position_, String imagePath_, int health_, int attack_) {
        this.possible_attacks = new ArrayList<>();
        this.position = position_;
        this.maxHealth = health_;
        this.currentHealth = this.maxHealth;
        this.attack = attack_;
        picLabel = new ImageIcon(Objects.requireNonNull(loadImage(imagePath_)));
    }

    @Override
    public Entity clone()
            throws SerializationException {
        return SerializationUtils.clone(this);
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


    //getters and setters ______________________________________________________
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public UnitEnum getColor() {
        return color;
    }

    public void setColor(UnitEnum color) {
        this.color = color;
    }

    public ImageIcon getPicLabel() {
        return picLabel;
    }

    public int getCurrentHealth() {
        return currentHealth;
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

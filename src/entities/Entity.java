package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public int worldX;
    public int worldY;
    public int speed;
    public String direction = null;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
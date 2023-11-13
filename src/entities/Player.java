package entities;

import inputs.KeyboardInputs;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.PlayerConstants.*;


public class Player extends Entity {

    GamePanel gamePanel;
    KeyboardInputs keyI;
    BufferedImage[][] animations;
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 10;
    private int playerDir;
    private int lastPlayerDir = 0;
    private int lastAniIndex = 1;
    private boolean moving = false;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyboardInputs keyI) {
        this.gamePanel = gp;
        this.keyI = keyI;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.sizeTile / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.sizeTile / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        loadAnimations();
    }

    public void setDefaultValues() {
        worldX = gamePanel.sizeTile * 23;
        worldY = gamePanel.sizeTile * 21;
        speed = 3;
    }

    public void update() {

        if (keyI.upPressed) {
            direction = "up";
            moving = true;
        } else if (keyI.downPressed) {
            direction = "down";
            moving = true;
        } else if (keyI.leftPressed) {
            direction = "left";
            moving = true;
        } else if (keyI.rightPressed) {
            direction = "right";
            moving = true;
        } else {
            direction = "notmoving";
            moving = false;
        }

        //CHECK TILE COLLISION
        collisionOn = false;
        gamePanel.collCheck.checkTile(this);

        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        setDirection(direction);
    }

    public void setDirection(String direction) {
        switch (direction) {
            case "up":
                playerDir = WALK_UP;
                break;
            case "down":
                playerDir = WALK_DOWN;
                break;
            case "left":
                playerDir = WALK_LEFT;
                break;
            case "right":
                playerDir = WALK_RIGHT;
                break;
            default:
                playerDir = lastPlayerDir;
        }

    }

    public void draw(Graphics g) {
        if (moving) {
            updateAnimationTick();
        }
        g.drawImage(animations[playerDir][aniIndex], screenX, screenY, gamePanel.sizeTile, gamePanel.sizeTile, null);
        lastAniIndex = aniIndex;
        lastPlayerDir = playerDir;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= WALK_SPRITES) {
                aniIndex = 0;
            }
        }
    }


    private void loadAnimations() {
        try {
            BufferedImage img = ImageIO.read(new File("res/player/caballero_sprites.png"));

            animations = new BufferedImage[4][4]; //[y][x]
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * gamePanel.sizeTile, j * gamePanel.sizeTile, gamePanel.sizeTile, gamePanel.sizeTile);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
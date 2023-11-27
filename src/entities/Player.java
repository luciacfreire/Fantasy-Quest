package entities;

import inputs.KeyboardInputs;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.GameStatesConstants.*;

public class Player extends Entity {

    KeyboardInputs keyI;

    BufferedImage[][] animationsAttack, animationsWalk;
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 10;
    private int playerDir;
    private int lastPlayerDir = 0;
    private int lastAniIndex = 1;
    private boolean moving = false;
    public final int screenX;
    public final int screenY;
    private int x = (int) (4.5 * gp.sizeTile);
    private int y = (int) (3.5 * gp.sizeTile);


    public Player(GamePanel gp, KeyboardInputs keyI) {
        super(gp);

        this.gp = gp;
        this.keyI = keyI;

        screenX = gp.screenWidth / 2 - (gp.sizeTile / 2);
        screenY = gp.screenHeight / 2 - (gp.sizeTile / 2);

        solidArea = new Rectangle(8, 16, 30, 30);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        animationsWalk = loadAnimations("Swordsman_walk");
        animationsAttack = loadAnimations("Swordsman_attack");
    }

    /**
     * Establece los valores predeterminados para la posición, velocidad y estado del jugador en el juego.
     * Además, define los valores predeterminados para la salud máxima y actual del jugador.
     */
    public void setDefaultValues() {
        worldX = gp.sizeTile * 28;
        worldY = gp.sizeTile * 26;
        speed = 3;

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    /**
     * Actualiza el estado del jugador en el juego, incluyendo la gestión de la dirección, la detección de colisiones con tiles y objetos,
     * y la actualización de la posición del jugador en consecuencia.
     */
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
        gp.collCheck.checkTile(this);


        //CHECK OBJECT COLLISION
        int objIndex = gp.collCheck.checkObject(this, true);
        pickUpObject(objIndex);

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

    /**
     * Establece la dirección del jugador en el juego con base en la dirección especificada.
     * Utiliza valores específicos para representar diferentes direcciones de movimiento.
     * @param direction La dirección del movimiento del jugador ("up", "down", "left", "right" o "notmoving").
     */
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

    /**
     * Realiza acciones específicas cuando el jugador recoge un objeto según su nombre.
     *
     * @param i Índice del objeto recogido en el array de objetos del juego.
     */

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "More Speed":
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Less Speed":
                    speed -= 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Oh, slow down!");
                    break;
                case "Door":
                    gp.gameState = battleState;
            }


        }

    }
    /**
     * Obtiene la imagen inicial del jugador para el estado de juego 'playState'.
     *
     * @return La imagen inicial del jugador.
     */
    public BufferedImage getInitialImage() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("res/player/Swordsman.png"));
            img = img.getSubimage(gp.originalSizeTile, 0, gp.originalSizeTile, gp.originalSizeTile);
            img = uTool.scaledImage(img, gp.sizeTile * 3, gp.sizeTile * 3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    /**
     * Dibuja la representación visual del jugador en el panel gráfico.
     *
     * @param g El objeto Graphics utilizado para dibujar.
     */

    public void draw(Graphics g) {

        if (gp.gameState == playState) {
            if (moving) {
                updateAnimationTick();
            }
            g.drawImage(animationsWalk[playerDir][aniIndex], screenX, screenY, null);
            lastAniIndex = aniIndex;
            lastPlayerDir = playerDir;
        }

        if (gp.gameState == battleState) {
            g.drawImage(getInitialImage(),x,y, null);
        }


    }

    /**
     * Actualiza el índice de animación del jugador, controlando la velocidad de la animación.
     */
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

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void moveOnAttackPlayer(boolean movingForward, int displacement) {
        if (movingForward) {
            // Lógica de movimiento hacia adelante
            this.setX(this.getX() + displacement);
        } else {
            // Lógica de movimiento hacia atrás
            this.setX(this.getX() - displacement);
        }
    }


}
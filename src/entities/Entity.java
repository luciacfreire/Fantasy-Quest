package entities;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity {

    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;
    public String direction = null;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    UtilityTool uTool = new UtilityTool();


    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity (GamePanel gp){
        this.gp = gp;
    }

    /**
     *  Reduce la vida del objeto en el juego en función del daño recibido.
     * @param damage La cantidad de daño que se aplicará al objeto.
     */
    public void receiveDamage(int damage){
        life -= damage;
        if(life < 0){
            life = 0;
        }
    }

    /**
     * Comprueba si el objeto en el juego ha sido derrotado, evaluando si su vida actual es igual o menor a cero.
     * @return  true si el objeto ha sido derrotado (vida <= 0), false en caso contrario.
     */
    public boolean isDefeated(){
        return life <= 0;
    }

    /**
     * Carga y escala las animaciones desde un archivo de imagen específico.
     *
     * @param path La ruta relativa al archivo de imagen que contiene las animaciones.
     * @return Una matriz bidimensional de BufferedImage representando las animaciones cargadas y escaladas.
     */
    public BufferedImage[][] loadAnimations(String path) {

        BufferedImage[][] animations;

        try {
            BufferedImage img = ImageIO.read(new File("res/player/" + path + ".png"));

            animations = new BufferedImage[4][4]; //[y][x]
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * gp.originalSizeTile, j * gp.originalSizeTile, gp.originalSizeTile, gp.originalSizeTile);
                    animations[j][i] = uTool.scaledImage(animations[j][i], gp.sizeTile, gp.sizeTile);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return animations;
    }
}
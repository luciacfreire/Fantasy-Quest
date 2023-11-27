package entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.GameStatesConstants.*;

public class Boss extends Entity{
    BufferedImage img;
    private int x = 8 * gp.sizeTile;
    private int y = (int) (3.5f * gp.sizeTile);
    public Boss(GamePanel gp) {
        super(gp);
        setDefaultValues();
        getBossImage();

    }

    /**
     * Establece los valores predeterminados para la vida de un objeto(jefe) en el juego.
     * Inicializa la vida actual y la vida máxima con un valor predeterminado (10 en este caso).
     */
    public void setDefaultValues() {
        maxLife = 10;
        life = maxLife;
    }

    /**
     * Carga la imagen del jefe del juego desde un archivo específico, realiza operaciones de recorte y escala,
     * y asigna la imagen resultante a la variable de clase 'img'.
     * Si hay un error durante el proceso de carga o manipulación de la imagen, se maneja mediante el lanzamiento
     * de una excepción de tiempo de ejecución.
     */

    public void getBossImage(){
        try {
            img = ImageIO.read(new File("res/player/RedDragon.png"));
            img = img.getSubimage(  0, 6*32, 32, 32);
            img = uTool.scaledImage(img, gp.sizeTile*4, gp.sizeTile*4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dibuja la imagen del jefe en el contexto gráfico proporcionado si el estado del juego es de batalla.
     * @param g
     */
    public void draw (Graphics g){
        if (gp.gameState == battleState) {
            g.drawImage( img,x , y, null);
        }
    }

    /**
     * Devuelve la coordenada x del objeto en el juego.
     * @return La coordenada x del objeto.
     */
    public int getX(){
        return x;
    }

    /**
     * Establece la coordenada x del objeto en el juego.
     * @param x La nueva coordenada x que se asignará al objeto.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Modifica la coordenada x del objeto en el juego durante un ataque del jefe, según la lógica de movimiento especificada.
     * @param movingForward Indica si el objeto se está moviendo hacia adelante durante el ataque.
     * @param displacement La cantidad de desplazamiento que se aplicará al objeto durante el ataque.
     */
    public void moveOnAttackBoss(boolean movingForward, int displacement) {
        if (movingForward) {
            // Lógica de movimiento hacia adelante
            this.setX(this.getX() - displacement);
        } else {
            // Lógica de movimiento hacia atrás
            this.setX(this.getX() + displacement);
        }
    }

}

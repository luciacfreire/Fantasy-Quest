package main;

import object.OBJ_Heart;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import static utilz.Constants.GameStatesConstants.*;

public class UI {
    Graphics g;
    BufferedImage heartFull, heartHalf, heartEmpty;
    GamePanel gamePanel;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    double playTime;
    DecimalFormat dF = new DecimalFormat("#0.00");
    public int commandNum = 0;
    public int commandNum2 = 0;


    //Default for box black
    public int boxX;
    public int boxY;
    public int boxWidth;
    public int boxHeight;

    int counter = 0;
    float alpha = 0f;
    int y;

    public UI (GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        //CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gamePanel);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
    }

    /**
     * Muestra un mensaje en la interfaz del juego.
     * @param text El texto del mensaje que se mostrará.
     */

    public void showMessage (String text){
        message = text;
        messageOn = true;
    }

    /**
     * Dibuja elementos en la interfaz del juego según el estado actual del juego.
     * @param g El contexto gráfico utilizado para realizar el dibujo.
     */
    public void draw (Graphics g){
        this.g = g;

        g.setFont(arial_40);
        g.setColor(Color.white);

        if(gamePanel.gameState == titleState ){
            drawTitleScreen();
        }

        if(gamePanel.gameState == playState){
            drawPlayerLife();
        }
        if(gamePanel.gameState == pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gamePanel.gameState == battleState){
            drawPlayerLife();
            drawBossLife();
        }
        if(gamePanel.gameState == loseState){
            drawFinalMessageL();
        }
        if(gamePanel.gameState == winState){
            drawFinalMessageW();

        }

    }


    /**
     * Dibuja un mensaje de felicitaciones para indicar que el jugador ha ganado el juego.
     */

    public void drawFinalMessageW(){

        g.setColor(Color.black);
        g.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);

        g.setFont(g.getFont().deriveFont(Font.BOLD,80F));
        g.setColor(Color.white);
        String text = "Congratulations";
        int x = getXforCenteredText(text);
        int y = gamePanel.sizeTile*3;
        g.drawString(text,x,y);


        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.BOLD,68F));
        text = "You win!";
        x = getXforCenteredText(text);
        y = gamePanel.sizeTile*5;
        g.drawString(text,x,y);
    }

    /**
     * Dibuja un mensaje de "Game Over" y opciones de menú después de que el jugador pierde el juego
     */

    public void drawFinalMessageL(){

        g.setColor(Color.black);
        g.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);

        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.BOLD,80F));
        String text = "Game Over";
        int x = getXforCenteredText(text);
        int y = gamePanel.sizeTile*3;
        g.drawString(text,x,y);


        //MENU
        g.setFont(g.getFont().deriveFont(Font.BOLD,44F));

        text = "Retry";
        x = getXforCenteredText(text);
        y += (int) (gamePanel.sizeTile*3.5);
        g.drawString(text,x,y);
        if(commandNum2 == 0){
            g.drawString(">",x-gamePanel.sizeTile,y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gamePanel.sizeTile;
        g.drawString(text,x,y);
        if(commandNum2 == 1){
            g.drawString(">",x-gamePanel.sizeTile,y);
        }
        }

    /**
     * Dibuja la pantalla de título del juego con el nombre del juego, una imagen de caballero y opciones de menú.
      */
    public void drawTitleScreen() {

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,gamePanel.screenWidth,gamePanel.screenHeight);

        //TITLE NAME
        g.setFont(g.getFont().deriveFont(Font.BOLD,82F));
        String text = "Fantasy Quest";
        int x = getXforCenteredText(text);
        int y = gamePanel.sizeTile*3;

        //SHADOW
        g.setColor(Color.gray);
        g.drawString(text,x+4,y+4);
        //MAIN COLOR
        g.setColor(Color.white);
        g.drawString(text,x,y);

        //CABALLERO IMAGE
        x = gamePanel.screenWidth/2 - gamePanel.sizeTile;
        y += gamePanel.sizeTile*2;
        g.drawImage(getImage(),x,y,gamePanel.sizeTile*2,gamePanel.sizeTile*2,null);

        //MENU
        g.setFont(g.getFont().deriveFont(Font.BOLD,44F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += (int) (gamePanel.sizeTile*3.5);
        g.drawString(text,x,y);
        if(commandNum == 0){
            g.drawString(">",x-gamePanel.sizeTile,y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gamePanel.sizeTile;
        g.drawString(text,x,y);
        if(commandNum == 1){
            g.drawString(">",x-gamePanel.sizeTile,y);
        }

    }

    /**
     * Dibuja la representación visual de la vida del jugador utilizando imágenes de corazones llenos, medio llenos y vacíos.
     * Este método utiliza las imágenes proporcionadas (heartFull, heartHalf, heartEmpty) para representar la vida actual
     *  * y máxima del jugador en forma de corazones en la pantalla de juego.
     */
    public void drawPlayerLife(){

        int x = gamePanel.sizeTile/2;
        int y = gamePanel.sizeTile/2;
        int i = 0;

        //DRAW EMPTY HEART
        while(i< gamePanel.player.maxLife/2){
            g.drawImage(heartEmpty,x,y,null);
            i++;
            x += gamePanel.sizeTile;
        }

        //RESET
        x = gamePanel.sizeTile/2;
        y = gamePanel.sizeTile/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i<gamePanel.player.life){
            g.drawImage(heartHalf,x,y,null);
            i++;
            if(i<gamePanel.player.life){
                g.drawImage(heartFull,x,y,null);
            }
            i++;
            x += gamePanel.sizeTile;
        }

    }

    /**
     * Dibuja la representación visual de la vida del jefe utilizando imágenes de corazones llenos, medio llenos y vacíos.
     */
    public void drawBossLife(){

        int x = gamePanel.sizeTile*10;
        int y = gamePanel.sizeTile/2;
        int i = 0;

        //DRAW EMPTY HEART
        while(i< gamePanel.boss.maxLife/2){
            g.drawImage(heartEmpty,x,y,null);
            i++;
            x += gamePanel.sizeTile;
        }

        //RESET
        x = gamePanel.sizeTile*10;
        y = gamePanel.sizeTile/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i<gamePanel.boss.life){
            g.drawImage(heartHalf,x,y,null);
            i++;
            if(i<gamePanel.boss.life){
                g.drawImage(heartFull,x,y,null);
            }
            i++;
            x += gamePanel.sizeTile;
        }

    }

    /**
     * Carga y devuelve una imagen del jugador.
     * @return La imagen del jugador cargada desde el archivo Swordsman_walk.png en la ruta "res/player/".
     */
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("res/player/Swordsman_walk.png"));
            image = image.getSubimage(0, 0, gamePanel.originalSizeTile, gamePanel.originalSizeTile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Dibuja la pantalla de pausa en el juego con el texto "PAUSED".
     * Este método establece la fuente y la posición del texto para crear una representación visual
     * de la pantalla de pausa centrada en la pantalla de juego.
     */
    public void drawPauseScreen(){

        g.setFont(g.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";

        int x = getXforCenteredText(text);

        int y = gamePanel.screenHeight/2;

        g.drawString(text, x, y);
    }

    /**
     * Calcula la posición x para centrar un texto en la pantalla de juego.
     * @param text El texto para el cual se calculará la posición centrada.
     * @return La posición x calculada para centrar el texto en la pantalla.
     */
    public int getXforCenteredText(String text){
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}



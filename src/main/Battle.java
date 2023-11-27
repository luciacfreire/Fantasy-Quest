package main;


import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.GameStatesConstants.*;

public class Battle {
    GamePanel gamePanel;
    private BufferedImage backgroundImage;
    public boolean playerTurn = false;
    public int bossX;
    public int playerX;
    public boolean moviendoseHaciaAdelantePlayer, moviendoseHaciaAdelanteBoss;
    public final int desplazamiento;

    public Battle(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        playerTurn = true;


        bossX = gamePanel.boss.getX();
        playerX = gamePanel.player.getX();
        desplazamiento = 12;

        moviendoseHaciaAdelantePlayer = true;
        moviendoseHaciaAdelanteBoss = true;
        getBackgroundImage();
    }

    /**
     * Actualiza el estado del juego y controla el flujo de turnos entre el jugador y el jefe.
     * Realiza acciones asociadas al ataque, como calcular el poder de ataque, aplicar daño y gestionar movimientos.
     * Verifica las condiciones de victoria o derrota y cambia el estado del juego en consecuencia.
     */
    public void update() {

        if (playerTurn) {

            if (gamePanel.keyI.attackPressed) {

                int bossAttackPower = generateRandomAttackPower(1, 3);
                int playerAttackPower = generateRandomAttackPower(1, 3);

                if (playerAttackPower > bossAttackPower) {
                    moviendoseHaciaAdelanteBoss = false;
                    moviendoseHaciaAdelantePlayer = true;
                    //Aplicar Daño al jefe
                    gamePanel.boss.receiveDamage(playerAttackPower);
                    //Cambiar posición del jugador
                    gamePanel.player.moveOnAttackPlayer(moviendoseHaciaAdelantePlayer, desplazamiento);
                    gamePanel.boss.moveOnAttackBoss(moviendoseHaciaAdelanteBoss, desplazamiento);

                    //Comprobamos si lo derrotamos
                    if (gamePanel.boss.isDefeated()) {
                        //VICTORIA
                        gamePanel.gameState = winState;
                    } else {
                        gamePanel.keyI.attackPressed = false;
                        playerTurn = false;
                    }
                } else {
                    moviendoseHaciaAdelantePlayer = false;
                    moviendoseHaciaAdelanteBoss = true;
                    gamePanel.player.moveOnAttackPlayer(moviendoseHaciaAdelantePlayer, desplazamiento);
                    gamePanel.boss.moveOnAttackBoss(moviendoseHaciaAdelanteBoss, desplazamiento);

                }
                gamePanel.keyI.attackPressed = false;
                playerTurn = false;
                moviendoseHaciaAdelanteBoss = false;
                moviendoseHaciaAdelantePlayer = false;

            }
        } else {

            //Cambiar turno a jefe
            int bossAttackPower = generateRandomAttackPower(1, 3);
            int playerAttackPower = generateRandomAttackPower(1, 3);

            if (bossAttackPower > playerAttackPower) {
                moviendoseHaciaAdelanteBoss = true;
                moviendoseHaciaAdelantePlayer = false;
                //Aplicar daño al jugador
                gamePanel.player.receiveDamage(bossAttackPower);
                gamePanel.player.moveOnAttackPlayer(moviendoseHaciaAdelantePlayer, desplazamiento);
                gamePanel.boss.moveOnAttackBoss(moviendoseHaciaAdelanteBoss, desplazamiento);
                //Comprobamos si fue derrotado
                if (gamePanel.player.isDefeated()) {
                    //DERROTA
                    gamePanel.gameState = loseState;

                } else {
                    gamePanel.keyI.attackPressed = false;
                    playerTurn = true;
                }
            } else {
                moviendoseHaciaAdelantePlayer = true;
                moviendoseHaciaAdelanteBoss = false;
                gamePanel.boss.moveOnAttackBoss(moviendoseHaciaAdelanteBoss, desplazamiento);
                gamePanel.player.moveOnAttackPlayer(moviendoseHaciaAdelantePlayer, desplazamiento);
            }

            //Cambiar turno a jugador
            gamePanel.keyI.attackPressed = false;
            playerTurn = true;
            moviendoseHaciaAdelanteBoss = false;
            moviendoseHaciaAdelantePlayer = false;

        }
    }

    /**
     * Genera y devuelve un valor aleatorio que representa el poder de ataque en un rango dado.
     * @param min El valor mínimo del rango (incluido).
     * @param max El valor máximo del rango (incluido).
     * @return Un valor aleatorio dentro del rango especificado, representando el poder de ataque.
     */
    private int generateRandomAttackPower(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Carga la imagen de fondo del juego desde un archivo específico y la asigna a la variable de clase 'backgroundImage'.
     * Si hay un error durante la carga de la imagen, se maneja mediante la impresión de la traza de la excepción o lanzando
     * una excepción de tiempo de ejecución.
     */
    public void getBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("res/background/Battle.jpg"));

        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dibuja la imagen de fondo en el panel de juego con las dimensiones especificadas.
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
    }
}

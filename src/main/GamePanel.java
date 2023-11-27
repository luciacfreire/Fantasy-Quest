package main;

import entities.Boss;
import entities.Player;
import inputs.KeyboardInputs;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static utilz.Constants.GameStatesConstants.*;


public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    public final int originalSizeTile = 16;  //16x16 tile
    final int scale = 3;
    public final int sizeTile = originalSizeTile * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * sizeTile;  //768 pixels
    public final int screenHeight = maxScreenRow * sizeTile; //576 pixels
    //WORLD SETTINGS
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;


    //INPUTS
    KeyboardInputs keyI;

    //ENTITIES
    public Player player;
    public Boss boss;

    //OBJECTS
    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //FOR UPDATE AND REPAINT
    private Thread gameThread;
    private final int FPS = 60;
    //WORLD MAP TILES
    TileManager tileM = new TileManager(this);
    public CollisionCheck collCheck = new CollisionCheck(this);

    //GAME STATE
    public int gameState;

    //BATTLE
    public Battle battle;



    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        keyI = new KeyboardInputs(this);
        addKeyListener(keyI);
        player = new Player(this, keyI);
        boss = new Boss(this);
        battle = new Battle(this);
        this.setFocusable(true);

    }

    /**
     * Prepara el juego inicializando objetos y estableciendo valores predeterminados
     */
    public void setUpGame() {
        aSetter.setObject();
        gameState = titleState;
        player.setDefaultValues();
        boss.setDefaultValues();
    }

    /**
     * Inicia un nuevo hilo (thread) para ejecutar el ciclo de juego.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    /**
     * Pinta el componente gráfico de la interfaz de usuario según el estado actual del juego.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g);
        }
        else if (gameState == loseState) {
            ui.draw(g);
        }
        else if (gameState == winState) {
            ui.draw(g);
        }
        else if (gameState == battleState){
            battle.draw(g);
            player.draw(g);
            if (boss != null) {
                boss.draw(g);
            }
            ui.draw(g);
        }
        //OTHERS
        else {
            //TILE
            tileM.draw(g);
            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g, this);
                }
            }
            //PLAYER
            player.draw(g);

            //UI
            ui.draw(g);
        }


        g.dispose();
    }



    public void update() {
        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            // nothing
        }
        if(gameState == battleState){

            battle.update();
        }
    }

    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}


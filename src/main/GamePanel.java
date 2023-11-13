package main;

import entities.Player;
import inputs.KeyboardInputs;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


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
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * sizeTile;
    public final int worldHeight = maxWorldRow * sizeTile;

    //INPUTS
    KeyboardInputs keyI;
    //ENTITIES
    public Player player;
    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);

    //FOR UPDATE AND REPAINT
    private Thread gameThread;
    private final int FPS = 60;
    //WORLD MAP TILES
    TileManager tileM = new TileManager(this);

    public CollisionCheck collCheck = new CollisionCheck(this);


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        keyI = new KeyboardInputs();
        addKeyListener(keyI);
        player = new Player(this, keyI);
        this.setFocusable(true);

    }

    public void setUpGame(){
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //TILE
        tileM.draw(g);
        //OBJECT
        for (int i = 0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g,this);
            }
        }
        //PLAYER
        player.draw(g);

        g.dispose();
    }

    public void update() {
        player.update();
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


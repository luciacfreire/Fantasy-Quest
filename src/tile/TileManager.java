package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNum = new int [gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("res/maps/world01.txt");
    }

    public void getTileImage() {
        try{
            BufferedImage img = ImageIO.read(new File("res/backgroundTiles/forest_tiles.png"));
            BufferedImage img2 = ImageIO.read(new File("res/backgroundTiles/sand.png"));
            BufferedImage img3 = ImageIO.read(new File("res/backgroundTiles/earth.png"));
            BufferedImage img4 = ImageIO.read(new File("res/backgroundTiles/wall.png"));
            BufferedImage img5 = ImageIO.read(new File("res/backgroundTiles/tree.png"));
            BufferedImage img6 = ImageIO.read(new File("res/backgroundTiles/grass.png"));


            //GRASS
            tile[0] = new Tile();
            tile[0].image = img6;
            /*
            tile[0] = new Tile();
            tile[0].image = img.getSubimage( 0*2*gamePanel.originalSizeTile, 0*2*gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile);
            */

            //FLOWERS
            /*
            tile[4] = new Tile();
            tile[4].image = img.getSubimage(2* gamePanel.originalSizeTile, 0*2* gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile);
            */


            //WATER
            tile[2] = new Tile();
            tile[2].image = img.getSubimage( 5*2* gamePanel.originalSizeTile, 8*2* gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile, 2*gamePanel.originalSizeTile);
            tile[2].collision = true;

            //SAND
            tile[5] = new Tile();
            tile[5].image = img2;

            //EARTH
            tile[3] = new Tile();
            tile[3].image = img3;

            //WALL
            tile[1] = new Tile();
            tile[1].image = img4;
            tile[1].collision = true;


            //TREE
            tile[4] = new Tile();
            tile[4].image = img5;
            tile[4].collision = true;


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics g){
       int worldCol = 0;
       int worldRow = 0;

       while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gamePanel.sizeTile;
           int worldY = worldRow * gamePanel.sizeTile;
           int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
           int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

           if(worldX + gamePanel.sizeTile > gamePanel.player.worldX - gamePanel.player.screenX &&
              worldX - gamePanel.sizeTile < gamePanel.player.worldX + gamePanel.player.screenX &&
              worldY + gamePanel.sizeTile > gamePanel.player.worldY - gamePanel.player.screenY &&
              worldY - gamePanel.sizeTile < gamePanel.player.worldY + gamePanel.player.screenY){

               g.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.sizeTile, gamePanel.sizeTile, null);

           }
           worldCol++;

           if(worldCol == gamePanel.maxWorldCol){
               worldCol = 0;
               worldRow++;
           }
       }
    }
}

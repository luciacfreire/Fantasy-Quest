package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public Tile[] tileElem;

    public int[][] objectMap;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[50];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("res/maps/worldV2.txt");
        tileElem = new Tile[10];
        objectMap = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getElemImage();
        loadObjectMap("res/maps/worldElem.txt");

    }


    public void getElemImage() {
        setup(9,"Trees",0,0,false, tileElem);
        setup(0,"Trees",1,0,true, tileElem);
        setup(1,"Houses",1,3,true, tileElem);
        setup(2,"Rocks",0,0,true, tileElem);
        setup(3,"Rocks",2,0,true, tileElem);
        setup(4,"WinterDeadTrees",3,0,true, tileElem);

    }

    public void loadObjectMap(String filePath){
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    objectMap[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {

        //PLACEHOLDER
        setup(0, "Grass", 0, 0, true, tile);
        setup(1, "Grass", 0, 0, true, tile);
        setup(2, "Grass", 0, 0, true, tile);
        setup(3, "Grass", 0, 0, true, tile);
        setup(4, "Grass", 0, 0, true, tile);
        setup(5, "Grass", 0, 0, true, tile);
        setup(6, "Grass", 0, 0, true, tile);
        setup(7, "Grass", 0, 0, true, tile);
        setup(8, "Grass", 0, 0, true, tile);
        setup(9, "Grass", 0, 0, true, tile);
        //PLACEHOLDER

        //Water
        setup(10, "Grass", 0, 0, true, tile);
        //Sand 1
        setup(11, "Grass", 3, 0, false, tile);
        //Sand 2
        setup(12, "Grass", 4, 0, false, tile);

        //Grass
        setup(13, "TexturedGrass", 0, 1, false, tile);
        setup(14, "TexturedGrass", 1, 1, false, tile);
        setup(15, "TexturedGrass", 2, 1, false, tile);

        //Dead Grass
        setup(16, "DeadGrass", 0, 1, false, tile);
        setup(17, "DeadGrass", 1, 1, false, tile);
        setup(18, "DeadGrass", 2, 1, false, tile);

        // CLIFF-WATER
        setup(19, "Cliff-Water", 0, 0, true, tile);
        setup(20, "Cliff-Water", 1, 0, true, tile);
        setup(21, "Cliff-Water", 2, 0, true, tile);
        setup(22, "Cliff-Water", 0, 1, true, tile);
        setup(23, "Cliff-Water", 2, 1, true, tile);
        setup(24, "Cliff-Water", 0, 2, true, tile);
        setup(25, "Cliff-Water", 1, 2, true, tile);
        setup(26, "Cliff-Water", 2, 2, true, tile);
        //inverse
        setup(27, "Cliff-Water", 3, 0, true, tile);
        setup(28, "Cliff-Water", 4, 0, true, tile);
        setup(29, "Cliff-Water", 3, 1, true, tile);
        setup(30, "Cliff-Water", 4, 1, true, tile);
    }

    public void setup(int index, String imageName, int x, int y, boolean collision, Tile[] tile) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            BufferedImage image = ImageIO.read(new File("res/background/" + imageName + ".png"));
            tile[index].image = image.getSubimage(x * gamePanel.originalSizeTile, y * gamePanel.originalSizeTile, gamePanel.originalSizeTile, gamePanel.originalSizeTile);
            tile[index].image = uTool.scaledImage(tile[index].image, gamePanel.sizeTile, gamePanel.sizeTile);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void draw(Graphics g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.sizeTile;
            int worldY = worldRow * gamePanel.sizeTile;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;


            if (worldX + gamePanel.sizeTile > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.sizeTile < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.sizeTile > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.sizeTile < gamePanel.player.worldY + gamePanel.player.screenY) {
                if (tile[tileNum].image != null) {
                    g.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
                //Dibuja elementos extra
                int objectNum = objectMap[worldCol][worldRow];
                if (objectNum != 9 && objectNum > -1 && objectNum < tileElem.length && tileElem[objectNum].image != null) {
                    g.drawImage(tileElem[objectNum].image, screenX, screenY, null);
                }
            }
            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

package main;

import entities.Entity;

public class CollisionCheck {
    private GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    private boolean checkCollision(int row, int col) {
        return gp.tileM.tile[gp.tileM.mapTileNum[col][row]].collision;
    }

    private int calculateRow(float worldY) {
        return (int) (worldY / gp.sizeTile);
    }

    private int calculateCol(float worldX) {
        return (int) (worldX / gp.sizeTile);
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = calculateCol(entityLeftWorldX);
        int entityRightCol = calculateCol(entityRightWorldX);
        int entityTopRow = calculateRow(entityTopWorldY);
        int entityBottomRow = calculateRow(entityBottomWorldY);

        switch (entity.direction) {
            case "up":
                entityTopRow = calculateRow(entityTopWorldY - entity.speed);
                entity.collisionOn = checkCollision(entityTopRow, entityLeftCol) || checkCollision(entityTopRow, entityRightCol);
                break;
            case "down":
                entityBottomRow = calculateRow(entityBottomWorldY + entity.speed);
                entity.collisionOn = checkCollision(entityBottomRow, entityLeftCol) || checkCollision(entityBottomRow, entityRightCol);
                break;
            case "left":
                entityLeftCol = calculateCol(entityLeftWorldX - entity.speed);
                entity.collisionOn = checkCollision(entityTopRow, entityLeftCol) || checkCollision(entityBottomRow, entityLeftCol);
                break;
            case "right":
                entityRightCol = calculateCol(entityRightWorldX + entity.speed);
                entity.collisionOn = checkCollision(entityTopRow, entityRightCol) || checkCollision(entityBottomRow, entityRightCol);
                break;
            default:
                entity.collisionOn = false;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){

                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }
}





















/*
public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.sizeTile;
        int entityRightCol = entityRightWorldX / gp.sizeTile;
        int entityTopRow = entityTopWorldY / gp.sizeTile;
        int entityBottomRow = entityBottomWorldY / gp.sizeTile;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.sizeTile;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.sizeTile;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.sizeTile;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.sizeTile;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
*/
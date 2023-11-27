package main;

import entities.Entity;

public class CollisionCheck {
    private GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Verifica si hay una colisión en la posición especificada en el mapa (Basic Tiles)
     * @param row La fila del mapa
     * @param col La columna  del mapa
     * @return true si hay una colisión en la posición dada, false de lo contrario
     */
    private boolean checkCollision(int row, int col) {
        return gp.tileM.tile[gp.tileM.mapTileNum[col][row]].collision;
    }

    /**
     * Verifica si hay una colisión en la posición especificada en el mapa (Elementos)
     * @param row La fila del mapa
     * @param col La columna  del mapa
     * @return true si hay una colisión en la posición dada, false de lo contrario
     */
    private boolean checkCollisionElem(int row, int col) {
        return gp.tileM.tileElem[gp.tileM.objectMap[col][row]].collision;
    }

    /**
     * Calcula el índice de fila correspondiente a la posición vertical en el mundo.
     * @param worldY La posición vertical en el mundo.
     * @return El índice de fila correspondiente a la posición dada en el mundo.
     */
    private int calculateRow(float worldY) {
        return (int) (worldY / gp.sizeTile);
    }

    /**
     * Calcula el índice de columna correspondiente a la posición vertical en el mundo.
     * @param worldX La posición horizontal en el mundo.
     * @return El índice de columna correspondiente a la posición dada en el mundo.
     */

    private int calculateCol(float worldX) {
        return (int) (worldX / gp.sizeTile);
    }


    /**
     * Verifica la colisión de una entidad con las casillas y elementos del mapa, ajustando la propiedad 'collisionOn'
     * @param entity entity La entidad cuya colisión se está verificando
     */
    public void checkTile(Entity entity) {
        boolean collisionOnTiles, collisionOnElems;
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
                collisionOnTiles = checkCollision(entityTopRow, entityLeftCol) || checkCollision(entityTopRow, entityRightCol);
                collisionOnElems = checkCollisionElem(entityTopRow, entityLeftCol) || checkCollisionElem(entityTopRow, entityRightCol);
                if(collisionOnTiles || collisionOnElems) entity.collisionOn = true;
                break;
            case "down":
                entityBottomRow = calculateRow(entityBottomWorldY + entity.speed);
                collisionOnTiles = checkCollision(entityBottomRow, entityLeftCol) || checkCollision(entityBottomRow, entityRightCol);
                collisionOnElems = checkCollisionElem(entityBottomRow, entityLeftCol) || checkCollisionElem(entityBottomRow, entityRightCol);
                if(collisionOnTiles || collisionOnElems) entity.collisionOn = true;

                break;
            case "left":
                entityLeftCol = calculateCol(entityLeftWorldX - entity.speed);
                collisionOnTiles = checkCollision(entityTopRow, entityLeftCol) || checkCollision(entityBottomRow, entityLeftCol);
                collisionOnElems = checkCollisionElem(entityTopRow, entityLeftCol) || checkCollisionElem(entityBottomRow, entityLeftCol);
                if(collisionOnTiles || collisionOnElems) entity.collisionOn = true;

                break;
            case "right":
                entityRightCol = calculateCol(entityRightWorldX + entity.speed);
                collisionOnTiles = checkCollision(entityTopRow, entityRightCol) || checkCollision(entityBottomRow, entityRightCol);
                collisionOnElems = checkCollisionElem(entityTopRow, entityRightCol) || checkCollisionElem(entityBottomRow, entityRightCol);
                if(collisionOnTiles || collisionOnElems) entity.collisionOn = true;

                break;
            default:
                entity.collisionOn = false;
        }

    }

    /**
     * Verifica la colisión de una entidad con los objetos del juego, ajustando la propiedad 'collisionOn'.
     * @param entity La entidad cuya colisión se está verificando.
     * @param player player Indica si la entidad es el jugador.
     * @return El índice del objeto con el que la entidad colisiona. Devuelve 999 si no hay colisión.
     */

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

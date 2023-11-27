package main;

import object.OBJ_LessSpeed;
import object.OBJ_MoreSpeed;
import object.OBJ_Door;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Inicializa y posiciona objetos en el juego, como objetos con velocidad reducida, objetos con velocidad aumentada y puertas.
     */
    public void setObject(){

        gp.obj[0] = new OBJ_LessSpeed(gp);
        gp.obj[0].worldX = 28 * gp.sizeTile;
        gp.obj[0].worldY = 17 * gp.sizeTile;

        gp.obj[1] = new OBJ_LessSpeed(gp);
        gp.obj[1].worldX = 44 * gp.sizeTile;
        gp.obj[1].worldY = 46 * gp.sizeTile;

        gp.obj[2] = new OBJ_MoreSpeed(gp);
        gp.obj[2].worldX = 42 * gp.sizeTile;
        gp.obj[2].worldY = 34 * gp.sizeTile;

        gp.obj[3] = new OBJ_MoreSpeed(gp);
        gp.obj[3].worldX = 13 * gp.sizeTile;
        gp.obj[3].worldY = 47 * gp.sizeTile;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 10 * gp.sizeTile;
        gp.obj[4].worldY = 30 * gp.sizeTile;
    }
}

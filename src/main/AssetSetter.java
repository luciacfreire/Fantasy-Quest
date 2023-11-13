package main;

import object.OBJ_LessSpeed;
import object.OBJ_MoreSpeed;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_LessSpeed(gp);
        gp.obj[0].worldX = 23 * gp.sizeTile;
        gp.obj[0].worldY = 8 * gp.sizeTile;

        gp.obj[1] = new OBJ_LessSpeed(gp);
        gp.obj[1].worldX = 12 * gp.sizeTile;
        gp.obj[1].worldY = 33 * gp.sizeTile;

        gp.obj[2] = new OBJ_MoreSpeed(gp);
        gp.obj[2].worldX = 35 * gp.sizeTile;
        gp.obj[2].worldY = 27 * gp.sizeTile;

        gp.obj[3] = new OBJ_MoreSpeed(gp);
        gp.obj[3].worldX = 25 * gp.sizeTile;
        gp.obj[3].worldY = 38 * gp.sizeTile;
    }
}

package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {

    public OBJ_Heart(GamePanel gamePanel){

    name ="Heart";

    try{

         image = ImageIO.read(new File("res/objects/heart_full.png"));
         image2 = ImageIO.read(new File("res/objects/heart_half.png"));
         image3 = ImageIO.read(new File("res/objects/heart_empty.png"));

        image = uTool.scaledImage(image, gamePanel.sizeTile, gamePanel.sizeTile);
        image2 = uTool.scaledImage(image2, gamePanel.sizeTile, gamePanel.sizeTile);
        image3 = uTool.scaledImage(image3, gamePanel.sizeTile, gamePanel.sizeTile);


    }catch(IOException e){
        e.printStackTrace();
    }

    collision =true;
}
}

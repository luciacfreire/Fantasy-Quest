package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OBJ_LessSpeed extends SuperObject{
    public OBJ_LessSpeed(GamePanel gamePanel){
        name = "Less Speed";

        try {
            BufferedImage imageRead = ImageIO.read(new File("res/player/enemigos.png"));
            image = imageRead.getSubimage(3*gamePanel.originalSizeTile,gamePanel.originalSizeTile,gamePanel.originalSizeTile,gamePanel.originalSizeTile);
            image = uTool.scaledImage(image, gamePanel.sizeTile, gamePanel.sizeTile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}

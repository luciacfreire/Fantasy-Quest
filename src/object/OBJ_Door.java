package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    public OBJ_Door(GamePanel gamePanel){
        name = "Door";

        try {
            BufferedImage imageRead = ImageIO.read(new File("res/objects/door.png"));
            image = uTool.scaledImage(imageRead, gamePanel.sizeTile, gamePanel.sizeTile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = false;
    }

}

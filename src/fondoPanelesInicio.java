import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class fondoPanelesInicio extends JPanel {
    private Image backgroundImage;
    public fondoPanelesInicio(String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            backgroundImage = img.getScaledInstance(FantasyQuestMainMenu.screen_width, FantasyQuestMainMenu.screen_height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Fallo");
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        }
    }
}

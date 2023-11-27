package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    /**
     * Escala una imagen original a un nuevo tamaño específico.
     * @param original La imagen original que se va a escalar.
     * @param width    El ancho deseado de la imagen escalada.
     * @param height   La altura deseada de la imagen escalada.
     * @return Una nueva imagen escalada al tamaño especificado.
     */
    public BufferedImage scaledImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics g = scaledImage.createGraphics();
        g.drawImage(original,0,0, width,height,null);
        g.dispose();

        return scaledImage;
    }
}

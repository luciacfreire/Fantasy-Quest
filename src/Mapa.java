// Clase Mapa
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mapa extends JPanel {

    private BufferedImage[][] background;
    public Color[][] mapaFondo;

    public Personaje personaje;

    public JPanel mapaFondo() {
        JPanel mapaFondo = new JPanel();
        mapaFondo.setLayout(null);
        return mapaFondo;
    }
    public Mapa(Personaje personaje) {
        this.personaje = personaje;
        // Inicializa la matriz de colores
        mapaFondo = new Color[FantasyQuestMainMenu.screen_width][FantasyQuestMainMenu.screen_height];
        // Configura el fondo del mapa
    }

    private void configurarFondoMapa() {
        for (int x = 0; x < FantasyQuestMainMenu.screen_width; x++) {
            for (int y = 0; y < FantasyQuestMainMenu.screen_height; y++) {
                // color fondo
                mapaFondo[x][y] = Color.GREEN;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja el fondo del mapa pixel por pixel
        for (int x = 0; x < FantasyQuestMainMenu.screen_width; x++) {
            for (int y = 0; y < FantasyQuestMainMenu.screen_height; y++) {
                g.setColor(mapaFondo[x][y]);
                g.fillRect(x, y, 1, 1); // Dibuja un píxel
            }
        }

        // Dibuja el personaje en el mapa como un cuadrado negro
        g.setColor(Color.BLACK);
        int personajeX = personaje.getX(); // Obtiene la posición X del personaje
        int personajeY = personaje.getY(); // Obtiene la posición Y del personaje
        g.fillRect(personajeX, personajeY, 20, 20); // Dibuja un cuadrado negro
    }

}

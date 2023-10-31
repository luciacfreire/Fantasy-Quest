// Clase Mapa
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mapa extends JPanel implements KeyListener  {

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
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        // Configura el fondo del mapa
        configurarFondoMapa();
    }


    public void keyReleased(KeyEvent e) {
    }

    private void configurarFondoMapa() {
        for (int x = 0; x < FantasyQuestMainMenu.screen_width; x++) {
            for (int y = 0; y < FantasyQuestMainMenu.screen_height; y++) {
                // color fondo
                mapaFondo[x][y] = Color.BLUE;
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
        g.fillRect(personajeX, personajeY, 80, 80); // Dibuja un cuadrado negro
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Mueve al personaje en función de las teclas presionadas
        if (keyCode == KeyEvent.VK_LEFT) {
            personaje.setX(personaje.getX() - 5);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            personaje.setX(personaje.getX() + 5);
        } else if (keyCode == KeyEvent.VK_UP) {
            personaje.setY(personaje.getY() - 5);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            personaje.setY(personaje.getY() + 5);
        }

        // Llama a repaint para actualizar la representación del personaje
        repaint();
    }

}

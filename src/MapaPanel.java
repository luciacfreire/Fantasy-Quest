// Clase MapaPanel
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class MapaPanel extends JPanel implements KeyListener  {

    public Image caballero;
    public Color[][] mapaFondo;

    public Personaje personaje;

    public JPanel mapaFondo() {
        JPanel mapaFondo = new JPanel();
        mapaFondo.setLayout(null);
        return mapaFondo;
    }
    public MapaPanel(Personaje personaje) {
        this.personaje = personaje;
        // Inicializa la matriz de colores
        mapaFondo = new Color[FantasyQuestMainMenu.screen_width][FantasyQuestMainMenu.screen_height];
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        try {
            BufferedImage personajeImage = ImageIO.read(new File("C:\\Users\\Lucía\\Juego_de_rol\\images\\caballero.jpg"));
            caballero = personajeImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Configura el fondo del mapa
        configurarFondoMapa();
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
        //g.fillRect(personajeX, personajeY, 80, 80); // Dibuja un cuadrado negro
        g.drawImage(caballero, personajeX, personajeY, this);
    }


    //
    //CREAR UN PACKAGE : INPUTS, Y UNA CLASE : KEYBOARDINPUTS IMPLEMENTS KEYLISTENER
    // private MapaPanel mapa;  public KeyboardInputs(MapaPanel mapa) { this.mapa = mapa; }
    // addKeylistener(new keyboardinputs(this)); y quitamos de arriba el implements KeyListener
    //CREAMOS FUNCIONES AQUI Y LAS LLAMAMOS EN LA CLASE KEYBINP, AÑADIENDO LOS VALORES PEDIDOS
    // EN EL PAINTCOMPONENT UTILIZAMOS LOS VALORES MODIFICADOS


    public void keyReleased(KeyEvent e) {
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

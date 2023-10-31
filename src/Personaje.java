// Clase Personaje
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Personaje extends JPanel implements KeyListener {
    private int x, y;
    private final int speed = 5;

    private String race;


    public Personaje() {

    }

    public String getRace(){
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }


    public Personaje(String race) {

        this.race = race;

        // Establece el tamaño y el color del personaje
        setBounds(x, y, 50, 50);
        setOpaque(true);
        setBackground(Color.RED);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    // Implementa los métodos KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            x -= speed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            x += speed;
        } else if (keyCode == KeyEvent.VK_UP) {
            y -= speed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            y += speed;
        }

        setLocation(x, y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
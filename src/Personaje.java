// Clase Personaje
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Personaje extends JPanel  {
    private int x;
    private int y;
    private final int speed = 10;



    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }


    public Personaje() {

        // Establece el tamaño y el color del personaje
        //setBounds(x, y, 100, 100);
        //setOpaque(true);
        //setBackground(Color.RED);
    }



    public void setY(int y) {
        this.y = y;
    }


    public void setX(int x) {
        this.x = x;
    }
}

// Implementa los métodos KeyListener

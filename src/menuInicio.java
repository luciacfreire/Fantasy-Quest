import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


// CLASE QUE CONFIGURA EL PANEL DE LA VENTANA DE ININIO
public class menuInicio  {
    public menuInicio() {
    }

    public JPanel panel() {
        JPanel panel = new fondoPanelesInicio(ventanaInicio.BACKGROUND_IMAGE_PATH);
        panel.setLayout(null);
        botones(panel);
        return panel;
    }

    //// Crea un panel para organizar los componentes y establecer una imagen de fondo
    /*
    public JPanel panel(){
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar y dibujar la imagen de fondo
                ImageIcon backgroundImage = createImageIcon("C:\\Users\\Lucía\\PRUEBA FQ\\assets\\img\\dragon_inicio.jpg");
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        panel.setLayout(null);

        botones(panel);

        return panel;
    }
    */






    //LEE LA URL DE LA IMAGEN
    /*
    public static ImageIcon createImageIcon(String path) {
        BufferedImage imgURL = null;
        try {
            imgURL = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Fallo");
        }

        if (imgURL != null) {
            System.out.println("Founded file: " + path);
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    */

    //CONFIGURACIÓN BOTONES
    public void botones(JPanel panel){
        // Crear botones
        JButton startButton = new JButton("Empezar Juego");
        JButton loadButton = new JButton("Juegos Anteriores");
        JButton exitButton = new JButton("Salir del Juego");

        //Establecer dimensión y localización
        startButton.setBounds((FantasyQuestMainMenu.screen_width - FantasyQuestMainMenu.screen_width / 4) / 2, (FantasyQuestMainMenu.screen_height / 8), FantasyQuestMainMenu.screen_width / 4, (FantasyQuestMainMenu.screen_height / 8));
        loadButton.setBounds((FantasyQuestMainMenu.screen_width - FantasyQuestMainMenu.screen_width / 4) / 2, 3 * (FantasyQuestMainMenu.screen_height / 8), FantasyQuestMainMenu.screen_width / 4, (FantasyQuestMainMenu.screen_height / 8));
        exitButton.setBounds((FantasyQuestMainMenu.screen_width - FantasyQuestMainMenu.screen_width / 4) / 2, 5 * (FantasyQuestMainMenu.screen_height / 8), FantasyQuestMainMenu.screen_width / 4, (FantasyQuestMainMenu.screen_height / 8));

        //Establecer el diseño de los botones
        //Diseño Boton start
        startButton.setContentAreaFilled(false); // Evita que el área de contenido del botón se llene
        startButton.setBorderPainted(false); // Evita que el borde del botón se pinte
        startButton.setFocusPainted(false); // Establece el BORDE DEL CUADRO DE TEXTO en false
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.setForeground(Color.WHITE);

        //Diseño Boton Load
        loadButton.setContentAreaFilled(false); // Evita que el área de contenido del botón se llene
        loadButton.setBorderPainted(false); // Evita que el borde del botón se pinte
        loadButton.setFocusPainted(false); // Establece el BORDE DEL CUADRO DE TEXTO en false
        loadButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loadButton.setForeground(Color.WHITE);

        //Diseño boton Exit
        exitButton.setContentAreaFilled(false); // Evita que el área de contenido del botón se llene
        exitButton.setBorderPainted(false); // Evita que el borde del botón se pinte
        exitButton.setFocusPainted(false); // Establece el BORDE DEL CUADRO DE TEXTO en false
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.setForeground(Color.WHITE);

        // Agregar los botones al panel
        panel.add(startButton);
        panel.add(loadButton);
        panel.add(exitButton);


        // Configurar acción para el botón "Empezar Juego"
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de selección de personaje
                SeleccionPersonaje seleccionPersonaje = new SeleccionPersonaje();
                seleccionPersonaje.setVisible(true);
                System.out.println("Comenzar juego");
            }
        });

        // Configurar acción para el botón "Juegos Anteriores"
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // lógica para cargar juegos anteriores

                System.out.println("Juegos anteriores");
            }
        });

        // Configurar acción para el botón "Salir del Juego"
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}

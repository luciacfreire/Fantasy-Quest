import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccionPersonaje extends JFrame {
    private JRadioButton magoRadioButton;
    private JRadioButton elfoRadioButton;
    private JRadioButton caballeroRadioButton;



    public Personaje PersonajeSeleccionado;
    public SeleccionPersonaje() {
        // Configura la ventana y su diseño
        setTitle("FQ - Selección de Personaje");
        setSize(FantasyQuestMainMenu.screen_width, FantasyQuestMainMenu.screen_height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un fondoPanelesInicio para la ventana de selección de personaje
        fondoPanelesInicio fondoPersonajes = new fondoPanelesInicio(ventanaInicio.BACKGROUND_IMAGE_PATH);
        setContentPane(fondoPersonajes);



        // Crea un grupo de botones de radio
        ButtonGroup buttonGroup = new ButtonGroup();

        // Crea los botones de radio para cada personaje
        magoRadioButton = new JRadioButton("Mago");
        elfoRadioButton = new JRadioButton("Elfo");
        caballeroRadioButton = new JRadioButton("Caballero");

        //Diseño de los botones
        //Diseño boton Mago
        magoRadioButton.setContentAreaFilled(false);
        magoRadioButton.setBorderPainted(false);
        magoRadioButton.setFocusPainted(false);
        magoRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        magoRadioButton.setForeground(Color.WHITE);

        //Diseño boton Elfo
        elfoRadioButton.setContentAreaFilled(false);
        elfoRadioButton.setBorderPainted(false);
        elfoRadioButton.setFocusPainted(false);
        elfoRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        elfoRadioButton.setForeground(Color.WHITE);

        //Diseño boton Caballero
        caballeroRadioButton.setContentAreaFilled(false);
        caballeroRadioButton.setBorderPainted(false);
        caballeroRadioButton.setFocusPainted(false);
        caballeroRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        caballeroRadioButton.setForeground(Color.WHITE);

        // Agrega los botones de radio al grupo
        buttonGroup.add(magoRadioButton);
        buttonGroup.add(elfoRadioButton);
        buttonGroup.add(caballeroRadioButton);

        // Agrega los botones de radio a la ventana
        add(magoRadioButton);
        add(elfoRadioButton);
        add(caballeroRadioButton);

        JButton okButton = new JButton("OK");

        okButton.setBounds((FantasyQuestMainMenu.screen_width - FantasyQuestMainMenu.screen_width / 4) / 2, 5 * (FantasyQuestMainMenu.screen_height / 8), FantasyQuestMainMenu.screen_width / 4, (FantasyQuestMainMenu.screen_height / 8));

        add(okButton);
        // Configura la disposición de los botones de radio según tus necesidades
        setLayout(new GridLayout(1, 3, 100, 100)); // 1 fila, 3 columnas

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de selección de personaje
                ventanaMapa mapa = new ventanaMapa(PersonajeSeleccionado);
                mapa.setVisible(true);
                System.out.println("Comenzar Mapa");
            }
        });

        // Agrega un ActionListener a cada botón de radio para manejar la selección del usuario
        magoRadioButton.addActionListener(e -> {
            // Lógica para cuando se selecciona "Mago"
            PersonajeSeleccionado = new Mago();
            System.out.println("Mago seleccionado");
        });

        elfoRadioButton.addActionListener(e -> {
            PersonajeSeleccionado = new Elfo();
            // Lógica para cuando se selecciona "Elfo"
            System.out.println("Elfo seleccionado");
        });

        caballeroRadioButton.addActionListener(e -> {
            PersonajeSeleccionado = new Caballero();
            // Lógica para cuando se selecciona "Caballero"
            System.out.println("Caballero seleccionado");
        });

        // Configura otras propiedades de la ventana

        // Ajusta el tamaño, posición, cierre y visibilidad de la ventana
    }
}

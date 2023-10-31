import javax.swing.JFrame;

//CLASE QUE CREA LA VENTANA DE INICIO
public class ventanaInicio extends JFrame{

    public static final String BACKGROUND_IMAGE_PATH = "C:\\Users\\Lucía\\Juego_de_rol\\images\\dragon_inicio.jpg";
    public ventanaInicio() {

        // Crear una ventana principal
        this.setTitle("Fantasy Quest");
        this.setSize(FantasyQuestMainMenu.screen_width, FantasyQuestMainMenu.screen_height);
        this.setContentPane(new menuInicio().panel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hacer visible la ventana
        this.setVisible(true);
    }

}


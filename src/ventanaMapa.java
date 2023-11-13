import javax.swing.*;

public class ventanaMapa extends JFrame {
    public ventanaMapa(Personaje PersonajeSeleccionado) {
        // Crear una ventana principal
        this.setTitle("Fantasy Quest");
        this.setSize(FantasyQuestMainMenu.screen_width, FantasyQuestMainMenu.screen_height);
        this.setContentPane(new MapaPanel(PersonajeSeleccionado));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hacer visible la ventana
        this.setVisible(true);
    }

}

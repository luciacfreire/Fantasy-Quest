package main;

import javax.swing.*;

public class GameWindow {
    private JFrame window;

    public GameWindow(GamePanel gamePanel) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fantasy Quest");
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }

}

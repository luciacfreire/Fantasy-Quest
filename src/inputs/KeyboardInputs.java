package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.Constants.GameStatesConstants.*;

public class KeyboardInputs implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    public boolean attackPressed;



    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();


        //TITLE STATE
        if(gamePanel.gameState == titleState) {
            if (code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {
                    gamePanel.gameState = playState;
                }
                if (gamePanel.ui.commandNum == 1) {
                    System.exit(0);
                }

            }
        }

        // L - STATE
        if(gamePanel.gameState == loseState){
            if (code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum2--;
                if(gamePanel.ui.commandNum2<0){
                    gamePanel.ui.commandNum2 = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum2++;
                if(gamePanel.ui.commandNum2>1){
                    gamePanel.ui.commandNum2 = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gamePanel.ui.commandNum2 == 0) {
                    gamePanel.setUpGame();
                }
                if(gamePanel.ui.commandNum2 == 1){
                    System.exit(0);
                }

            }
        }

        //BATTLE STATE

        if(gamePanel.gameState == battleState){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                attackPressed = true;
            }
        }

        //PLAY STATE
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gamePanel.gameState == playState){
                gamePanel.gameState = pauseState;
            }
            else if(gamePanel.gameState == pauseState){
                gamePanel.gameState = playState;
            }
        }

    }



    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if(gamePanel.gameState == battleState){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                attackPressed = false;
            }
        }


    }
}

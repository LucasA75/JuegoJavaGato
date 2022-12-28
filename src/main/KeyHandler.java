package main;
import java.awt.event.*;


//Esto es para hacer un input con el teclado -> que el personaje se mueva mientras toco una tecla
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //debug
    public boolean checkdrawTime = false;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //retorna un entero keycode con la llave en este evento

        // Pulsador del Menu
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){ // al precionar una tecla si esta es la W
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }

            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                //Esto no deja que se salga el cursor de las 2 opciones
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 0;
                }
                else if(gp.ui.commandNum >= 2){
                    gp.ui.commandNum= 0;
                }
            }

            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;

                }
                if(gp.ui.commandNum == 1){
                    //Saldra del juego
                    System.exit(0);
                }
            }

            
        }


        
        if(code == KeyEvent.VK_W){ // al precionar una tecla si esta es la W
            upPressed = true; // dile a uppressed que es true , es false por defecto
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
        //Debug
        if(code == KeyEvent.VK_T){
            if(checkdrawTime == false){
                checkdrawTime = true;
            }
            else if(checkdrawTime == true){
                checkdrawTime = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

          if(code == KeyEvent.VK_W){ // al soltar una tecla si esta es la W
            upPressed = false; // dile a uppressed que es false 
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}

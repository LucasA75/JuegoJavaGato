package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] agrs) {
        
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esto es para que se pueda cerrar la ventana
        window.setResizable(false); // no se puede escalar la ventana
        window.setTitle("2D adventure"); // El titulo de la ventana

        window.setLocationRelativeTo(null); // Esto dejara la ventana al medio
        window.setVisible(true);// Esto mostrara verdaderamente la ventana
        
        //Estoy agregando a la ventana el gamepanel
        window.add(gamePanel);
        // Con pack hare que la ventana se ajuste a la escala del gamepanel
        window.pack();
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}

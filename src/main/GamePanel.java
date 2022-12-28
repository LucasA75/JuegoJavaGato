package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import models.Entity;
import models.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ // esto llama a las funciones de Panel 
    //Esto funciona como el panel de juego
    Font arial_20;
    //Screen Settings
    final int originalTileSize = 16; // 16 px del arte
    //Ya que ahora ocupamos pantallas mas grandes o si no se veria muy pequeño
    //lo escalaremos
    final int scale = 3;
    public final int titlesize = originalTileSize * scale; // 48px tile

    //definir la proporcion de la pantalla
    public final int maxScreenCol = 16;
    public final int maxSreenRow = 12;
    public final int screenWidth = titlesize* maxScreenCol; // 768 px
    public final int screenHeigth = titlesize * maxSreenRow; // 576 px

    //World SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //Collision checker
    public Collision checker = new Collision(this);
    
    //FPS
    int  fps = 60;

    //Invocar a TileManager
    TileManager tileM = new TileManager(this);
    //Invocar a KeyHandler
    KeyHandler keyH = new KeyHandler(this);
    //Invocamos al Audio
    Sound sound = new Sound();
    Sound se = new Sound();
    //Invocamos al Assetsetter
    public AssetSetter aSetter = new AssetSetter(this);
    //Invocamos al UI
    public UI ui = new UI(this);
    //Esto se ocupa para hacer parar el tiempo del juego
    Thread gameThread;



    //invocaremos al Player
    public Player player = new Player(this,keyH);

    //invocamos al objeto
    public SuperObject  obj[] = new SuperObject[10]; // Esto quiere decir que podemos mostrar en pantalla hasta 10 obj

    //Invocamos al NPC
    public ArrayList<Entity> npc = new ArrayList<Entity>();

    //Estado del Juego
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;




/*     //Setear la posicion por defecto del jugador
// Esto es para probar el sistema la primera vez
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
 */





    //definimos el constructor
    public GamePanel(){
        //esto es las dimenciones 
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeigth));
        //Esto el background del juego
        this.setBackground(Color.white);
        //Esto hara un mejor renderizado del juego
        this.setDoubleBuffered(true);
        //Esto vera las teclas que estoy o no pulsando
        this.addKeyListener(keyH);
        this.setFocusable(true); //Con esto, el gamePanel se concentrara en recibir el input del teclado

    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        //Aqui reproducimos la musica 
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    //Esto se crea al implementar el runnable
    @Override
    public void run() { // Aqui se creara el core de nuestro juego
        
        double drawInterval = 1000000000/fps; // el numero es 1 seg -> dibujaremos en pantalla cada 0.016666666 seg
        //double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        
        //Mientras gamethread existe se repite lo siguiente
        while(gameThread != null){
/*             //aqui restringimos los FPS
            //long currentTime = System.nanoTime(); // nanoTime retorna el valor del java virtual machine time en nano segundos
            
            //1 Update : update la informacion como la posicion del personaje
            update();
            //2 draw : dibujar en pantalla con la informacion modificada
            repaint(); // con esto llamamos al paintComponent
            
        
            try {
                 //Esto es el tiempo que queda entre que muestra algo y muestra algo nuevo
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // esto lo que hace es por el slepp que acepta solo milisegundos y no nano segundos



                if(remainingTime < 0 ){
                    remainingTime = 0;
                }



                Thread.sleep((long) remainingTime); // esto pausa 

                nextDrawTime = drawInterval + nextDrawTime;



                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } */


            // ESTA ES OTRA FORMA DE HACER LO DE ARRIBA

            currentTime = System.nanoTime();

            delta = delta + (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                // System.out.println("FPS"+ drawCount); // Esto muestra los FPS
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
    
        if(gameState == playState){
            //Player
            player.update();
            //NPC
            for(int i = 0 ; i< npc.size() ; i++){
                if(npc.get(i) != null){
                    npc.get(i).update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
    }

    //Pintar componentes
    public void paintComponent(Graphics g){ //graphics es una clase que tiene funciones para diobujar objetos en la ventana
        //Este graphics es como un paintbrush
        super.paintComponent(g); // este super llama a JPanel

        Graphics2D g2 = (Graphics2D)g; // Esto nos da mas opciones para trabajar con la geometria del 2D

        //Debug
        long drawStart = 0;
        if(keyH.checkdrawTime == true){
            drawStart = System.nanoTime();
        }
        

        //Title screen
        if(gameState == titleState){
            ui.draw(g2);
        }
        else{
                    //Primero dibujamos los tiles y luego el personaje
        tileM.draw(g2);

        //dibujamos al objeto
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }

        //NPC
        for(int i = 0; i<npc.size();i++){
            if(npc.get(i) != null){
                npc.get(i).draw(g2);
            }
        }

        //dibujamos al player
        player.draw(g2);
        
        //UI
        ui.draw(g2);


        //DEBUG -> Tiene una funcion modo on and off
        if(keyH.checkdrawTime == true){
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.white);
        g2.setFont(arial_20);
        g2.drawString("Draw Time: " + passed , 10, 400);
        System.out.println("Draw Time "+ passed);
        }

        g2.dispose(); // dispone de estos graficos contexto y libera cualquier recurso del sistema que se este ocupando
        }
    }

public void playMusic(int i){
    sound.setFile(i);
    sound.play();
    sound.loop();
}
public void stopMusic(){
    sound.stop();
}
public void playSE(int i){
    se.setFile(i);
    se.play();

}
}

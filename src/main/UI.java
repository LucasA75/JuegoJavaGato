package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_key;

public class UI {
    //Aqui haremos el Ui del juego 

    GamePanel gp;
    // Por temas de optimizacion lo haremos fuera del loop esta declaracion
    Font arial_40, arial_80B,arial_20;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0 ;
    public int commandNum = 0;


    Graphics2D g2;

    //Para terminar el juego
    public boolean gameFinished = false;
    //Para hacer un timer 
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        //Como se mostrara en el GP
        arial_20 = new Font("Arial", Font.PLAIN,20);  
        arial_40 = new Font("Arial", Font.PLAIN,40);
        arial_80B = new Font("Arial", Font.BOLD,80);
        OBJ_key key = new OBJ_key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        //Verificamos si terminamos el juego 
        if (gameFinished == true){
        
            g2.setFont(arial_40);
            g2.setColor(Color.white);
        
            String text;
            int textLength;
            int x;
            int y;

            text = "Encontraste las Monedas!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeigth/2 - (gp.titlesize*3);
            g2.drawString(text, x, y);

            text = "Tu tiempo fue: "+ dFormat.format(playTime) + " !";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeigth/2 + (gp.titlesize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Felicidades!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeigth/2 + (gp.titlesize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

            


        }else{ //Si no hemos terminado el juego

        //Aqui damos la fuente 
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.titlesize/2, gp.titlesize/2,gp.titlesize,gp.titlesize,null);
        g2.drawString("x "+ gp.player.hasKey, 74, 65);
        
        //Tiempo
        playTime +=(double)1/60;
        g2.setFont(arial_20);
        g2.drawString("Tiempo: "+ dFormat.format(playTime), gp.titlesize*13,gp.titlesize*1);



        //Mensaje
        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.titlesize/2, gp.titlesize*5);
            //sumo 1 al contador -> como se esta haciendo un loop mientras esto se muestra , el framerate es de 60 por Segundo
            messageCounter++;
            //Por ello aqui 120 son 2 segundos donde se muestra el mensaje en pantalla
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        

        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }


        if(gp.gameState == gp.playState){
        
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        
        









    }
    }

    public void drawPauseScreen(){
        String text= "Pausa";
        int x = getXforCenteredText(text);
        int y = gp.screenHeigth/2;
    
        g2.drawString(text,x,y);
    }
    
    public int getXforCenteredText(String text){
    
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
    
        return x;
    }

    public void drawTitleScreen(){

        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text = "Cuida Tus Monedas";
        int x = getXforCenteredText(text);
        int y = gp.titlesize * 3;

        //Sombra
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+2, y+2);
        //Main color
        g2.setColor(Color.orange);
        g2.drawString(text,x,y);

        //Imagen 
        x= gp.screenWidth/2 - (gp.titlesize*2)/2;
        y += gp.titlesize*2;
        g2.drawImage(gp.player.down1,x, y, gp.titlesize*2,gp.titlesize*2,null);


        //Menu
        
        
        //New Game
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,36F));
        text = "Nuevo Juego";
         x = getXforCenteredText(text);
         y += gp.titlesize * 3.5;
         g2.drawString(text, x, y);
         if(commandNum == 0){
            g2.drawString(">", x-gp.titlesize, y);
         }


        //quit
         g2.setFont(g2.getFont().deriveFont(Font.BOLD,36F));
        text = "Salir";
         x = getXforCenteredText(text);
         y += gp.titlesize;
         g2.drawString(text, x, y);
         if(commandNum == 1){
            g2.drawString(">", x-gp.titlesize, y);
         }

    }

}





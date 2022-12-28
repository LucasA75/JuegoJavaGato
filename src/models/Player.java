package models;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;


public class Player extends Entity {
    
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    //constructor
    public Player(GamePanel gp, KeyHandler keyH){


        super(gp); // Esto significa que llamamos al constructor de nuestro extend
        this.keyH = keyH;
        // Aqui modificamos la camara del jugador
        screenX = gp.screenHeigth/2 + 70 ;
        screenY = gp.screenWidth/3;

        //solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 24;
        solidArea.height = 24; 
        //Esto de arriba es lo mismo que esto de abajo -> pero para ocupar estos parametros de mejor manera los dejamos separados
        //solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        
        worldX = gp.titlesize * 23;
        worldY = gp.titlesize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
           /*  //Antes traia la imagen del jugador asi
            this.stay = ImageIO.read(new FileInputStream("/Generation/Juego Java/res/player/stay1.png")); */ // Esto guarda la imagen
            
            this.up1 = setup("stay1");
            this.up2 = setup("stay1");
            this.stay = setup("stay1");
            this.iz = setup("iz");
            this.iz1= setup("iz1");
            this.iz2= setup("iz2");
            this.der= setup("der");
            this.der1= setup("der1");
            this.der2= setup("der2");
            this.down1 = setup("stay1");
            this.down2 = setup("stay1");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();

        BufferedImage image = null;

        try {

            image = ImageIO.read(new FileInputStream("././././res/player/"+ imageName +".png"));
            image = uTool.scaleImage(image, gp.titlesize, gp.titlesize);


        } catch (IOException e) {
            // TODO: handle exception
        }
        return image;
    }



    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            //Aqui estoy modificando la posicion del character
            if(keyH.upPressed== true){
            direction = "up";
        }
        else if(keyH.downPressed == true){
            direction = "down";
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            
        }
        else if( keyH.rightPressed == true){
            direction = "right";
            
        }

        //colisionador
        collisionOn = false;
        gp.checker.checkTile(this);

        //chekear si colisiona con un objeto
        int objIndex = gp.checker.checkObject(this, true);
        pickUpObject(objIndex);


        //Me tira un error aqui si activo esto
        //Checkear la colision con un NPC
        //int npcIndex = gp.checker.checkEntity(this, gp.npc);
        //interactNPC(npcIndex); 

        //Prueba hecha por mi
        /* gp.checker.verificador1(this, true); */



        // si la colision es falsa , el jugador se movera
        if(collisionOn == false){
            switch(direction){
                case"up":
                worldY = worldY - speed; // esto va como el plano carteciano , arriba worldY derecha + -> abajo worldY izquierda -
                break;
                case "down":
                worldY = worldY + speed;
                break;
                case "left":
                worldX = worldX - speed;
                break;
                case "right":
                worldX = worldX + speed;
                break;
            }
        }

        //Esto es el cambiador de sprites
        spriteCounter++;
        if(spriteCounter > 3){
            if(spriteNum == 1){
                spriteNum =2;
            }
            else if(spriteNum == 2){
                spriteNum = 3;
            }
            else if(spriteNum == 3){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        }
        else{
            spriteNum = 1;
        }
    }

    //Esta funcion ve el objeto en pantalla y ve si el jugador choca con el y hace algo
    public void pickUpObject( int i){

        if(i != 999){
            
            String objName = gp.obj[i].name;

            switch(objName){
                case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("Obtuviste una LLave!");
                break;
                case "door":
                if(hasKey > 0){
                    gp.playSE(3);
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("Abriste una Puerta!");
                }
                else{
                    gp.ui.showMessage("Necesitas una llave!");
                }
                System.out.println("Key: "+hasKey);
                break;
                case "boots":
                gp.playSE(2);
                speed += 2;
                gp.obj[i] = null;
                gp.ui.showMessage("Obtuviste unas Botas!");
                break;

                case "chest":
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                break;
            }
        }

    }

    public void interactNPC(int i){
        if(i != 999){
            System.out.println("Chocaste con un NPC");
        }
    }

    public void draw(Graphics2D g2) {

    /*  g2.setColor(Color.white);
    g2.fillRect(x, worldY, gp.titlesize, gp.titlesize); */

    BufferedImage image = null;

    switch(direction){
        case "up":
        image = stay;
        break;
        
        case "down":
        image = stay;
        break;

        case "left":
        //Aqui se coloca como hacer una iteracion entre mas de 1 sprite
        if(spriteNum == 1){
            image = iz;
        }
        if(spriteNum == 2){
            image = iz1;
        }
        if(spriteNum == 3){
            image = iz2;
        }
        
        break;

        case "right":
        if(spriteNum == 1){
            image = der;
        }
        if(spriteNum == 2){
            image = der1;
        }
        if(spriteNum == 3){
            image = der2;
        }
        break;

    }
    g2.drawImage(image, screenX,screenY,null); // dibuja una imagen en pantalla
    }
}

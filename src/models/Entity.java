package models;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;;

public class Entity {
    
    public int worldX,worldY;
    public int speed;
    GamePanel gp;
    //Con esto guardaremos nuestros archivos
    public BufferedImage der,der1,der2,iz,iz1,iz2,stay,up1,up2,down1,down2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // podemos crear un rectangulo de colision
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }
    // Esto va ha ser utilizado por todos los NPC
    public void update(){

        setAction();
        collisionOn = false;
        gp.checker.checkTile(this);
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











    //Es una funcion que me trae las imagenes de las carpetas 
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
   
        BufferedImage image = null;
   
        try {
   
            image = ImageIO.read(new FileInputStream(imagePath +".png"));
            image = uTool.scaleImage(image, gp.titlesize, gp.titlesize);
   
   
        } catch (IOException e) {
            // TODO: handle exception
        }
        return image;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // esto es lo que saca la posicion del jugador respecto a donde esta en el mundo
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //Esto delimita la creacion del mundo -> para que no se cree todo mientras jugamos
        if( worldX + gp.titlesize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.titlesize - 500< gp.player.worldX + gp.player.screenX &&
            worldY + gp.titlesize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.titlesize - 500 < gp.player.worldY + gp.player.screenY){
                

                switch(direction){
                    case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    if(spriteNum == 3){
                        image = up1;
                    }
                    
                    break;
                    
                    case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    if(spriteNum == 3){
                        image = down1;
                    }
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
            g2.drawImage(image, screenX, screenY, gp.titlesize,gp.titlesize,null);
        }

    }
}

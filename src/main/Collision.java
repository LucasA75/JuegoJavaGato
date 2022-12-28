package main;

import java.util.ArrayList;

import models.Entity;

public class Collision {

    GamePanel gp;
    
    public Collision(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        
        //Aqui estoy colocando los 4 lados del cuadrado de colision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titlesize;
        int entityRightCol= entityRightWorldX/gp.titlesize;
        int entityTopRow = entityTopWorldY/gp.titlesize;
        int entityBottomRow = entityBottomWorldY/gp.titlesize;

        int tileNum1, tileNum2;


        switch(entity.direction){
            case "up":

            entityTopRow = ( entityTopWorldY - entity.speed)/gp.titlesize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){

                entity.collisionOn = true;
            }
            break;

            case "down":
            entityBottomRow = ( entityBottomWorldY + entity.speed)/gp.titlesize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){

                entity.collisionOn = true;
            }
            break;

            case "left":
            entityLeftCol = ( entityLeftWorldX - entity.speed)/gp.titlesize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){

                entity.collisionOn = true;
            }
            break;

            case "right":
            entityRightCol = ( entityRightWorldX + entity.speed)/gp.titlesize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){

                entity.collisionOn = true;
            }
            break;
        }
    }

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for(int i=0;i< gp.obj.length;i++){
            if(gp.obj[i] != null){

                //Sacar la posicion del area solida de la entity
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Sacar la posicion del area solida del objeto
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "down":
                    entity.solidArea.y +=entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
            
        }
        return index;
    }

    //NPC or Monster
     public int checkEntity(Entity entity, ArrayList<Entity> tarjet){
        
        int index = 999;

        for(int i=0;i< tarjet.size();i++){
            if(tarjet.get(i) != null){

                //Sacar la posicion del area solida de la entity
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Sacar la posicion del area solida del objeto
                tarjet.get(i).solidArea.x = tarjet.get(i).worldX + tarjet.get(i).solidArea.x;
                tarjet.get(i).solidArea.y = tarjet.get(i).worldY + tarjet.get(i).solidArea.y;

                switch(entity.direction){
                    case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(tarjet.get(i).solidArea)){
   
                            entity.collisionOn = true;
                            index = i;
                        }
                    
                    break;
                    case "down":
                    entity.solidArea.y +=entity.speed;
                    if(entity.solidArea.intersects(tarjet.get(i).solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                    break;
                    case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(tarjet.get(i).solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                    
                    break;
                    case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(tarjet.get(i).solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            tarjet.get(i).solidArea.x = tarjet.get(i).solidAreaDefaultX;
            tarjet.get(i).solidArea.y = tarjet.get(i).solidAreaDefaultY;

            }
        return index;
    }
    
    public void verificador(Entity entity, ArrayList<Entity> tarjet){
        if(entity.solidArea.intersects(tarjet.get(0).solidArea)){
           System.out.println("Te chocaste conmigo tontito"); 
           //Esto tira el mensaje cada vez que me muevo
        }
    }
   /*  public void verificador1(Entity entity, boolean player){
        for(int i=0;i< gp.obj.length;i++){
            if(gp.obj[i] != null){
        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
           System.out.println("Te chocaste conmigo tontito"); 
           //Esto tira el mensaje cada vez que me muevo
        }
    }
}
} */
}
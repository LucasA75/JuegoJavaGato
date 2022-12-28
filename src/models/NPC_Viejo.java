package models;
import java.util.Random;

import main.GamePanel;


public class NPC_Viejo extends Entity {

    public NPC_Viejo(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;

        getNpcImage();

        //Si queremos que sea estatico se coloca todo lo de solidArea que esta en player aca
    }
    public void getNpcImage(){
        /*  //Antes traia la imagen del jugador asi
         this.stay = ImageIO.read(new FileInputStream("/Generation/Juego Java/res/player/stay1.png")); */ // Esto guarda la imagen
         
         this.up1 = setup("././././res/npc/oldman_up_1");
         this.up2= setup("././././res/npc/oldman_up_2");
         this.iz= setup("././././res/npc/oldman_left_1");
         this.iz1= setup("././././res/npc/oldman_left_1");
         this.iz2= setup("././././res/npc/oldman_left_2");
         this.der1= setup("././././res/npc/oldman_right_1");
         this.der2= setup("././././res/npc/oldman_right_2");
         this.der= setup("././././res/npc/oldman_right_2");
         this.down1= setup("././././res/npc/oldman_down_1");
         this.down2= setup("././././res/npc/oldman_down_2");
 }

 //Aqui colocaremos las accioines que hara el npc
 public void setAction(){


            
        actionLockCounter++;

        if(actionLockCounter == 120){
            //Este es una IA super simple
            Random random = new Random();
            int i = random.nextInt(100)+1; // coge un numero desde el 1 al 100
    
            if(i <= 25){
                direction = "up";
            }
            if(i>25 && i <=50){
                direction = "down";
            }
            if(i>50 && i <= 75){
                direction = "left";
            }
            if(i>75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
        
 }
        
}


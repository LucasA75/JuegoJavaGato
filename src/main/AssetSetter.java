package main;

import models.NPC_Viejo;
import object.OBJ_Boots;
import object.OBJ_chest;
import object.OBJ_door;
import object.OBJ_key;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    //Aqui seteo donde estaran los objetos en el mapa
    public void setObject(){
        
        gp.obj[0] = new OBJ_key(gp);
        gp.obj[0].worldX = 23 *gp.titlesize;
        gp.obj[0].worldY = 7 * gp.titlesize;

        gp.obj[1] = new OBJ_key(gp);
        gp.obj[1].worldX = 23 *gp.titlesize;
        gp.obj[1].worldY = 40 * gp.titlesize;

        gp.obj[2] = new OBJ_key(gp);
        gp.obj[2].worldX = 37 *gp.titlesize;
        gp.obj[2].worldY = 8 * gp.titlesize;

        gp.obj[3] = new OBJ_door(gp);
        gp.obj[3].worldX = 10 *gp.titlesize;
        gp.obj[3].worldY = 12 * gp.titlesize;

        gp.obj[4] = new OBJ_door(gp);
        gp.obj[4].worldX = 8 *gp.titlesize;
        gp.obj[4].worldY = 28 * gp.titlesize;

        gp.obj[5] = new OBJ_door(gp);
        gp.obj[5].worldX = 12 *gp.titlesize;
        gp.obj[5].worldY = 23 * gp.titlesize;

        gp.obj[6] = new OBJ_chest(gp);
        gp.obj[6].worldX = 10 *gp.titlesize;
        gp.obj[6].worldY = 8 * gp.titlesize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 37 *gp.titlesize;
        gp.obj[7].worldY = 42 * gp.titlesize;



    }

    public void setNPC(){
        gp.npc.add(new NPC_Viejo(gp));
        gp.npc.get(0).worldX = 21 *gp.titlesize;
        gp.npc.get(0).worldY = 21 * gp.titlesize;
    }

}

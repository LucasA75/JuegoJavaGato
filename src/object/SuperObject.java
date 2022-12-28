package object;
import java.awt.image.*;
import java.awt.*;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48); //Cada objeto tendra esta area solida
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX; // esto es lo que saca la posicion del jugador respecto a donde esta en el mundo
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //Esto delimita la creacion del mundo -> para que no se cree todo mientras jugamos
        if( worldX + gp.titlesize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.titlesize - 500< gp.player.worldX + gp.player.screenX &&
            worldY + gp.titlesize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.titlesize - 500 < gp.player.worldY + gp.player.screenY){
        
            g2.drawImage(image, screenX, screenY, gp.titlesize,gp.titlesize,null);
        }
    }

}

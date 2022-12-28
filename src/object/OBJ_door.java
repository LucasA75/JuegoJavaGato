package object;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_door extends SuperObject {
    
    GamePanel gp;

    public OBJ_door(GamePanel gp){
        this.gp = gp;
        name = "door";
        try {
            image = ImageIO.read(new FileInputStream("././././res/object/door.png"));
            uTool.scaleImage(image, gp.titlesize, gp.titlesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}


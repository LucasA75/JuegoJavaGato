package object;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_chest extends SuperObject {
    
    GamePanel gp;

    public OBJ_chest(GamePanel gp){
        this.gp = gp;
        name = "chest";
        try {
            image = ImageIO.read(new FileInputStream("././././res/object/chest.png"));
            uTool.scaleImage(image, gp.titlesize, gp.titlesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

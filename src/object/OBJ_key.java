package object;

import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;


public class OBJ_key extends SuperObject{
    
    GamePanel gp;

    public OBJ_key(GamePanel gp){
        this.gp = gp;
        name = "Key";
        try {
            image = ImageIO.read(new FileInputStream("././././res/object/key.png"));
            uTool.scaleImage(image, gp.titlesize, gp.titlesize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

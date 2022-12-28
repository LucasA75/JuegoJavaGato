package object;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp){

        this.gp = gp;
        name = "boots";
        try {
            image = ImageIO.read(new FileInputStream("././././res/object/boots.png"));
            uTool.scaleImage(image, gp.titlesize, gp.titlesize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


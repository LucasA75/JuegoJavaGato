package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        //esto es para optimizar el trabajo de print 
        //Ancho , Largo , Tipo de imagen
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
            Graphics2D g2 = scaledImage.createGraphics(); //Crea un grafico 2d que puede ser usado como imagen buff
            g2.drawImage(original, 0, 0, width,height,null); // Aqui le figo que la imagen escalada se imprimira en pantalla en titlesize
            g2.dispose();

            return scaledImage;
    }
    
}

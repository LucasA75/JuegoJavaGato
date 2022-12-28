package tile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    //constructor
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("././././res/maps/worldV2.txt");
    }

    public void getTileImage(){
            
            //Todo esto ocupa la funcion setup para importar las imagenes y darle el atributo de colision y meterlos al array

            //Todo lo que hace el place holder es que como no ocuparemos el el mapa numeros del 0 al 9 , 
            //solo numeros de 2 digitos evitaremos asi algun error y el txt de mapa es mas legible
            //PlaceHolder 
            setup(0, "grass00", false);
            setup(1, "grass00", false);
            setup(2, "grass00", false);
            setup(3, "grass00", false);
            setup(4, "grass00", false);
            setup(5, "grass00", false);
            setup(6, "grass00", false);
            setup(7, "grass00", false);
            setup(8, "grass00", false);
            setup(9, "grass00", false);

            //Fin Place holder 1 - 9

            setup(10, "grass00", false);
            setup(11, "grass01", false);
            setup(12, "water00", false);
            setup(13, "water01", true);
            setup(14, "water02", true);
            setup(15, "water03", true);
            setup(16, "water04", true);
            setup(17, "water05", true);
            setup(18, "water06", true);
            setup(19, "water07", true);
            setup(20, "water08", true);
            setup(21, "water09", true);
            setup(22, "water10", true);
            setup(23, "water11", true);
            setup(24, "water12", true);
            setup(25, "water13", true);
            setup(26, "road00", false);
            setup(27, "road01", false);
            setup(28, "road02", false);
            setup(29, "road03", false);
            setup(30, "road04", false);
            setup(31, "road05", false);
            setup(32, "road06", false);
            setup(33, "road07", false);
            setup(34, "road08", false);
            setup(35, "road09", false);
            setup(36, "road10", false);
            setup(37, "road11", false);
            setup(38, "road12", false);
            setup(39, "earth", false);
            setup(40, "wall", true);
            setup(41, "tree", true);




            //tierra
            //Antes se hacia asi
            /* tile[1] = new Tile();//pared
            tile[1].image = ImageIO.read(new FileInputStream("/Generation/Juego Java/res/tiles/032.png"));
            tile[1].collision = true; */
    }

    public void setup(int index, String imageName, boolean collision){
        //Aqui trataremos todo el duplicado de lineas( instanciacion, importar la imagen , scala y la colision)
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("././././res/tiles/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.titlesize, gp.titlesize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void loadMap(String filePath){
        try {
            //Aqui llamamos al archivo de txt
            InputStream is = new FileInputStream(filePath);
            //Con esto podremos leer el formato de txt que le pasamos
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col<gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); // esto lee una linea del txt

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void draw(Graphics2D g2){
        
        int col = 0;
        int row = 0;
        

        // Esto hara el proceso de dibujado de casillas de manera auto
        while(col< gp.maxWorldCol && row < gp.maxWorldRow){

            int tileNum = mapTileNum[col][row];
            //Vamos a crear la camra del mundo
            int worldX = col * gp.titlesize;
            int worldY = row * gp.titlesize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // esto es lo que saca la posicion del jugador respecto a donde esta en el mundo
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //Esto delimita la creacion del mundo -> para que no se cree todo mientras jugamos
            if( worldX + gp.titlesize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.titlesize - 500< gp.player.worldX + gp.player.screenX &&
                worldY + gp.titlesize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.titlesize - 500 < gp.player.worldY + gp.player.screenY){
            
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            
            col++;


            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

        /* Asi podermos probar de manera manual como se veria un Tile
        g2.drawImage(tile[0].image, 0, 0, gp.titlesize,gp.titlesize,null);
        g2.drawImage(tile[1].image, 48, 0, gp.titlesize,gp.titlesize,null);
        g2.drawImage(tile[2].image, 96, 0, gp.titlesize,gp.titlesize,null); */
    }
}

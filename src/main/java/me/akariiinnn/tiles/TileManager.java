package me.akariiinnn.tiles;

import me.akariiinnn.main.GamePanel;
import me.akariiinnn.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];


    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldV2.txt");
    }

    public void getTileImage() {
        for(int i = 0; i < 10; i++)
        {
            tiles[i] = null;
        }
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
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
        setup(39, "dirt00", false);
        setup(40, "brick00", true);
        setup(41, "tree", true);
        setup(42, "island00", true);
        setup(43, "island01", true);
        setup(44, "island02", true);
        setup(45, "island03", true);
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();

        try{
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tiles[index].image = utilityTool.scaleImage(tiles[index].image, gp.tileSize, gp.tileSize);
            tiles[index].collision = collision;
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String file) {

        try{
            InputStream iS = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(iS));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                col = 0;
                row++;
                }
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if(worldX + (gp.tileSize * 2) > gp.player.worldX - gp.player.screenX &&
                worldX - (gp.tileSize * 2) < gp.player.worldX + gp.player.screenX &&
                worldY + (gp.tileSize * 2) > gp.player.worldY - gp.player.screenY &&
                worldY - (gp.tileSize * 2) < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

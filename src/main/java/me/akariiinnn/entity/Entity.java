package me.akariiinnn.entity;

import me.akariiinnn.main.GamePanel;
import me.akariiinnn.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    public String direction = "down";
    GamePanel gp;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48 ,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String name = null;
    String[] dialogues = new String[20];
    int howManyDialogues = 0;
    public int width, height;

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {

        this.gp = gp;
    }

    public void setAction() {}

    public void update() {

        setAction();

        collisionOn = false;
        gp.cHandler.checkTile(this);
        gp.cHandler.checkPlayer(this);
        gp.cHandler.checkEntity(this, gp.npc);
        gp.cHandler.checkEntity(this, gp.obj);

        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15 && speed != 0) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 3;
            } else if (spriteNumber == 3) {
                spriteNumber = 4;
            } else if (spriteNumber == 4) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }else if (speed == 0)
        {
            spriteNumber = 1;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if(worldX + (gp.tileSize * 2) > gp.player.worldX - gp.player.screenX &&
                worldX - (gp.tileSize * 2) < gp.player.worldX + gp.player.screenX &&
                worldY + (gp.tileSize * 2) > gp.player.worldY - gp.player.screenY &&
                worldY - (gp.tileSize * 2) < gp.player.worldY + gp.player.screenY) {

            switch(direction) {
                case "up":
                    if(spriteNumber == 1) {
                        image = up1;
                    }
                    if(spriteNumber == 2) {
                        image = up2;
                    }
                    if(spriteNumber == 3) {
                        image = up1;
                    }
                    if(spriteNumber == 4) {
                        image = up3;
                    }
                    break;
                case "down":
                    if(spriteNumber == 1) {
                        image = down1;
                    }
                    if(spriteNumber == 2) {
                        image = down2;
                    }
                    if(spriteNumber == 3) {
                        image = down1;
                    }
                    if(spriteNumber == 4) {
                        image = down3;
                    }
                    break;
                case "left":
                    if(spriteNumber == 1) {
                        image = left1;
                    }
                    if(spriteNumber == 2) {
                        image = left2;
                    }
                    if(spriteNumber == 3) {
                        image = left1;
                    }
                    if(spriteNumber == 4) {
                        image = left3;
                    }
                    break;
                case "right":
                    if(spriteNumber == 1) {
                        image = right1;
                    }
                    if(spriteNumber == 2) {
                        image = right2;
                    }
                    if(spriteNumber == 3) {
                        image = right1;
                    }
                    if(spriteNumber == 4) {
                        image = right3;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, this.width, this.height, null);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public void speak() {
        if(dialogues[howManyDialogues] == null)
            howManyDialogues = 0;

        gp.ui.currentDialogue = dialogues[howManyDialogues];
        howManyDialogues++;

        switch(gp.player.direction) {
            case"up":
                direction = "down";
                break;
            case"down":
                direction = "up";
                break;
            case"left":
                direction = "right";
                break;
            case"right":
                direction = "left";
                break;
        }
    }
}

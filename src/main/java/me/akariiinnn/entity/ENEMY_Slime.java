package me.akariiinnn.entity;

import me.akariiinnn.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class ENEMY_Slime extends Entity{
    public int baseSpeed;

    public ENEMY_Slime(GamePanel gp) {
        super(gp);

        direction = "left";

        this.speed = 1;
        this.width = gp.tileSize;
        this.height = gp.tileSize;
        this.damages = 1;
        baseSpeed = speed;
        solidArea = new Rectangle(4, 8, 8, 8);
        solidArea.x = solidArea.x * gp.scale;
        solidArea.y = solidArea.y * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = solidArea.width * gp.scale;
        solidArea.height = solidArea.height * gp.scale;
        this.name = "Slime";

        getImage();
    }

    public void getImage() {
        up1 = setup("/enemy/SlimeBack1", gp.tileSize, gp.tileSize);;
        up2 = setup("/enemy/SlimeBack2", gp.tileSize, gp.tileSize);
        up3 = setup("/enemy/SlimeBack3", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/SlimeGauche1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/SlimeGauche2", gp.tileSize, gp.tileSize);
        left3 = setup("/enemy/SlimeGauche3", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/SlimeDroite1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/SlimeDroite2", gp.tileSize, gp.tileSize);
        right3 = setup("/enemy/SlimeDroite3", gp.tileSize, gp.tileSize);
        down1 = setup("/enemy/SlimeFace1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/SlimeFace2", gp.tileSize, gp.tileSize);
        down3 = setup("/enemy/SlimeFace3", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter > 90) {
            Random rand = new Random();
            int n = rand.nextInt(5);

            // Makes the slime move towards the player vertically, horizontally and diagonally
            /*if (playerInRange(gp.player)) {
                if (gp.player.worldX - worldX > 0) {
                    if (gp.player.worldY - worldY > 0) {
                        direction = "down";
                    } else if (gp.player.worldY - worldY < 0) {
                        direction = "up";
                    } else {
                        direction = "right";
                    }
                } else if (gp.player.worldX - worldX < 0) {
                    if(gp.player.worldY - worldY > 0) {
                        direction = "down";
                    } else if (gp.player.worldY - worldY < 0) {
                        direction = "up";
                    } else {
                        direction = "left";
                    }
                } else if (gp.player.worldY - worldY > 0) {
                    if(gp.player.worldX - worldX > 0) {
                        direction = "right";
                    } else if (gp.player.worldX - worldX < 0) {
                        direction = "left";
                    } else {
                        direction = "down";
                    }
                } else if (gp.player.worldY - worldY < 0) {
                    if(gp.player.worldX - worldX > 0) {
                        direction = "right";
                    } else if (gp.player.worldX - worldX < 0) {
                        direction = "left";
                    } else {
                        direction = "up";
                    }
                }
            }
            else {*/
                switch (n) {
                    case 0:
                        speed = baseSpeed;
                        direction = "up";
                        break;
                    case 1:
                        speed = baseSpeed;
                        direction = "down";
                        break;
                    case 2:
                        speed = baseSpeed;
                        direction = "left";
                        break;
                    case 3:
                        speed = baseSpeed;
                        direction = "right";
                        break;
                    case 4:
                        speed = 0;
                        break;
                }

                System.out.println(direction);
                actionLockCounter = 0;
            //}
        }
    }

    public boolean playerInRange(Player p) {

        return p.worldX - worldX < 200 && p.worldX - worldX > -200 && p.worldY - worldY < 200 && p.worldY - worldY > -200;
    }
}

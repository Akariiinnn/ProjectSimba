package me.akariiinnn.entity;

import me.akariiinnn.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class ENEMY_Slime extends Entity{
    public ENEMY_Slime(GamePanel gp) {
        super(gp);

        direction = "left";

        this.speed = 2;
        this.width = gp.tileSize;
        this.height = gp.tileSize;
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
        if (actionLockCounter > 30) {
            Random rand = new Random();
            int n = rand.nextInt(5);
            System.out.println(n);

            // Make the slime move towards player if player in range
            //if(playerInRange(gp.player)) {
            //    if(gp.player.worldX > worldX) {direction = "right";}
            //    if(gp.player.worldX < worldX) {direction = "left";}
            //    if(gp.player.worldY > worldY) {direction = "down";}
            //    if(gp.player.worldY < worldY) {direction = "up";}
            //    System.out.println(direction);
            //}
            //else {
                switch (n) {
                    case 0: direction = "up"; break;
                    case 1: direction = "down"; break;
                    case 2: direction = "left"; break;
                    case 3: direction = "right"; break;
                    case 4: speed = 0; break;
                }
            //}
            actionLockCounter = 0;
        }
    }

    public boolean playerInRange(Player p) {

        return p.worldX - worldX < 200 && p.worldX - worldX > -200 && p.worldY - worldY < 200 && p.worldY - worldY > -200;
    }
}

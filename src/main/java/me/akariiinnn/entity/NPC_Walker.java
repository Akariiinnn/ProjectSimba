package me.akariiinnn.entity;

import me.akariiinnn.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Walker extends Entity{

    int baseSpeed;

    public NPC_Walker(GamePanel gp, String name, int speed, int width, int height) {

        super(gp);

        direction = "down";
        this.speed = speed;
        this.width = width;
        this.height = height;
        baseSpeed = speed;
        solidArea = new Rectangle(4, 8, 8, 8);
        solidArea.x = solidArea.x * gp.scale;
        solidArea.y = solidArea.y * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = solidArea.width * gp.scale;
        solidArea.height = solidArea.height * gp.scale;
        this.name = name;

        getImage();
        setDialogues();
    }

    public void getImage() {

        up1 = setup("/npc/" + name + "Back1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/" + name + "Back2", gp.tileSize, gp.tileSize);
        up3 = setup("/npc/" + name + "Back3", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/" + name + "Face1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/" + name + "Face2", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/" + name + "Face3", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/" + name + "Gauche1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/" + name + "Gauche2", gp.tileSize, gp.tileSize);
        left3 = setup("/npc/" + name + "Gauche3", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/" + name + "Droite1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/" + name + "Droite2", gp.tileSize, gp.tileSize);
        right3 = setup("/npc/" + name + "Droite3", gp.tileSize, gp.tileSize);
    }

    public void setDialogues() {

        dialogues[0] = "Hello, I'm Lin from Honduras :p";
        dialogues[1] = "I like playing league of legends";
        dialogues[2] = "I sleep really late cause I'm staying up all nights \nwith my friends and i'm always sleepy, it's bad :(";
    }

    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

            if (i <= 20) {
                speed = baseSpeed;
                direction = "up";
            }
            if (i > 20 && i <= 40) {
                speed = baseSpeed;
                direction = "down";
            }
            if (i > 40 && i <= 60) {
                speed = baseSpeed;
                direction = "left";
            }
            if (i > 60 && i <= 80) {
                speed = baseSpeed;
                direction = "right";
            }
            if (i > 80 && i <= 100) {
                speed = 0;
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}

package me.akariiinnn.entity;

import me.akariiinnn.main.GamePanel;
import me.akariiinnn.main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int invincibilityFrames = 0;

    public boolean hasBoots = false;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        this.width = 48;
        this.height = 48;

        solidArea = new Rectangle(4, 8, 8, 8);

        solidArea.x = solidArea.x * gp.scale;
        solidArea.y = solidArea.y * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = solidArea.width * gp.scale;
        solidArea.height = solidArea.height * gp.scale;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/player/Back1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/Back2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/Back3", gp.tileSize, gp.tileSize);
        down1 = setup("/player/Face1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/Face2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/Face3", gp.tileSize, gp.tileSize);
        left1 = setup("/player/Gauche1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/Gauche2", gp.tileSize, gp.tileSize);
        left3 = setup("/player/Gauche3", gp.tileSize, gp.tileSize);
        right1 = setup("/player/Droite1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/Droite2", gp.tileSize, gp.tileSize);
        right3 = setup("/player/Droite3", gp.tileSize, gp.tileSize);
    }

    public void update() {

        collisionOn = false;

        //CHECK NPC COLLISION
        int npcIndex = gp.cHandler.checkEntity(this, gp.npc);
        int enemyIndex = gp.cHandler.checkEntity(this, gp.enemies);
        interactNPC(npcIndex);
        interactENEMY(enemyIndex);

        if(keyH.ePressed && enemyIndex != 999)
        {
            //if player is next to an enemy by a third of a tileSize, attack it
            if(gp.enemies[enemyIndex] instanceof ENEMY_Slime)
            {
                if(gp.enemies[enemyIndex].worldX - worldX < gp.tileSize/3 && gp.enemies[enemyIndex].worldX - worldX > -gp.tileSize/3)
                {
                    if(gp.enemies[enemyIndex].worldY - worldY < gp.tileSize/3 && gp.enemies[enemyIndex].worldY - worldY > -gp.tileSize/3)
                    {
                        gp.enemies[enemyIndex] = null;
                        System.out.println(gp.enemies[enemyIndex].life);
                    }
                }
            }
        }

        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            gp.cHandler.checkTile(this);


            //CHECK OBJECT COLLISION
            int objIndex = gp.cHandler.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK EVENT
            gp.eventHandler.checkEvent();

            gp.keyH.ePressed = false;


            //IF COLLISION IS FALSE, PLAYER CAN MOVE, IF SHIFT PRESSED, SPRINT
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        if (keyH.shiftPressed && hasBoots) {
                            direction = "up";
                            worldY -= speed * 2;
                        } else {
                            direction = "up";
                            worldY -= speed;
                        }
                        break;
                    case "down":
                        if (keyH.shiftPressed && hasBoots) {
                            direction = "down";
                            worldY += speed * 2;
                        } else {
                            direction = "down";
                            worldY += speed;
                        }
                        break;
                    case "left":
                        if (keyH.shiftPressed && hasBoots) {
                            direction = "left";
                            worldX -= speed * 2;
                        } else {
                            direction = "left";
                            worldX -= speed;
                        }
                        break;
                    case "right":
                        if (keyH.shiftPressed && hasBoots) {
                            direction = "right";
                            worldX += speed * 2;
                        } else {
                            direction = "right";
                            worldX += speed;
                        }
                        break;
                }
            }


            spriteCounter++;
            int animFPS;
            if (keyH.shiftPressed && hasBoots) {
                animFPS = 7;
            } else {
                animFPS = 15;
            }
            if (spriteCounter > animFPS) {
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
            }
        }

        if(invincibilityFrames > 0)
        {
            invincibilityFrames++;
        }
        if(invincibilityFrames == 60)
        {
            invincibilityFrames = 0;
        }
        System.out.println(invincibilityFrames);
    }

    private void interactENEMY(int enemyIndex) {

            if(enemyIndex != 999) {
                if(gp.enemies[enemyIndex] instanceof ENEMY_Slime) {
                    if(invincibilityFrames == 0)
                    {
                        this.life -= 1;
                        System.out.println(this.life);
                        invincibilityFrames++;
                    }
                }
            }
    }

    private void interactNPC(int i) {

        if(i != 999) {
            if(gp.npc[i] instanceof NPC_Walker) {
                if(gp.keyH.ePressed)
                {
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                    gp.npc[i].actionLockCounter = 0;
                    gp.keyH.ePressed = false;
                }
            }
        }
    }

    public void pickUpObject(int i) {

        if(i != 999) {

        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

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

        g2.drawImage(image, screenX, screenY, null);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }
}

package me.akariiinnn.main;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.objects.OBJ_Heart;
import me.akariiinnn.objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    String keyBoardDisposition;
    // Font arial_80, arial_40, arial_25;

    Font maruMonica, purisaB;

    public boolean messageOn = false;
    public String message = "";
    BufferedImage heart, half_heart, blank_heart;
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int commandNumbers;
    public int titleScreenState = 0; // 0: the first menu screen, 1: the second screen


    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heartobj = new OBJ_Heart(gp);
        heart = heartobj.image;
        half_heart = heartobj.image2;
        blank_heart = heartobj.image3;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    private void drawPlayerLife() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX HEALTH
        while(i < gp.player.maxLife/2) {
            g2.drawImage(blank_heart, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT HEALTH
        while(i < gp.player.life) {
            g2.drawImage(half_heart, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(heart, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    private void drawTitleScreen() {

        if(gp.keyH.keyBoard == gp.keyH.azerty)
        {
            keyBoardDisposition = "AZERTY";
        }else if(gp.keyH.keyBoard == gp.keyH.qwerty) {
            keyBoardDisposition = "QWERTY";
        }

        if(titleScreenState == 0) {
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 85F));
            String text = "Project Simba";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // TEXT SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5,y+5);

            // TEXT COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Player Image
            x = gp.screenWidth/2 - (gp.tileSize*2) / 2;
            y += gp.tileSize;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Keyboard disposition : " + keyBoardDisposition;
            x = gp.tileSize*9;
            y = gp.tileSize*11 + 25;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0)
            {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Tank";
            x = getXforCenteredText(text);
            y += gp.tileSize*1;
            g2.drawString(text, x, y);
            if(commandNum == 1)
            {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Bruiser";
            x = getXforCenteredText(text);
            y += gp.tileSize*1;
            g2.drawString(text, x, y);
            if(commandNum == 2)
            {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Assassin";
            x = getXforCenteredText(text);
            y += gp.tileSize*1;
            g2.drawString(text, x, y);
            if(commandNum == 3)
            {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 4)
            {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }
    }

    private void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 30;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 175);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;

        return x;
    }
}

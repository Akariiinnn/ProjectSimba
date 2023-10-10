package me.akariiinnn.main;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.entity.Player;
import me.akariiinnn.tiles.TileManager;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 pixels tiles
    public final int scale = 3;

    public int tileSize = originalTileSize * scale; // 48x48 pixels tiles
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public CollisionHandler cHandler = new CollisionHandler(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread;
    public EventHandler eventHandler = new EventHandler(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity npc[] = new Entity[10];
    public Entity obj[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public final int titleState = 0;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(55, 147, 255));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        assetSetter.setObject();
        assetSetter.setNPC();
        //playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        if(gameState == playState) {
            //PLAYER
            player.update();

            //NPC
            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null) npc[i].update();
            }
        }
        if(gameState == pauseState) {
            //nothing
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        //OTHERS
        else {
            //TILE
            tileM.draw(g2);

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity object : obj) {
                if(object != null) {
                    entityList.add(object);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY * (e1.height/tileSize), e2.worldY * (e2.height/tileSize));
                    return result;
                }
            });

            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            for(int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }



            ui.draw(g2);

        }
        //DEBUG
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        System.out.println(se.soundURL[i]);
        se.play();
    }
}

package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) {

        super(gp);
        name = "Door";
        this.width = gp.tileSize;
        this.height = gp.tileSize;
        down1 = setup("/objects/DOOR", this.width, this.height);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

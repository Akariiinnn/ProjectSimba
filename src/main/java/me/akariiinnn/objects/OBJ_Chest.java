package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp) {

        super(gp);
        name = "Chest";
        collision = true;
        this.width = gp.tileSize;
        this.height = gp.tileSize;

        down1 = setup("/objects/CHEST", this.width, this.height);
    }
}

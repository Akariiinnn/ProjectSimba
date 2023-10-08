package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {

        super(gp);
        name = "Key";
        this.width = gp.tileSize;
        this.height = gp.tileSize;

        down1 = setup("/objects/KEY", this.width, this.height);
    }
}

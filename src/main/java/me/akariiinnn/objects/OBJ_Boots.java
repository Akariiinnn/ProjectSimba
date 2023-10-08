package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp) {

        super(gp);
        name = "Boots";

        this.width = gp.tileSize;
        this.height = gp.tileSize;
        down1 = setup("/objects/SHOES", this.width, this.height);
    }
}

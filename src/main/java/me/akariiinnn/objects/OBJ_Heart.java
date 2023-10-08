package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {

        super(gp);
        name = "Heart";

        this.width = gp.tileSize;
        this.height = gp.tileSize;

        image = setup("/objects/heart", width, height);
        image2 = setup("/objects/half_heart", width, height);
        image3 = setup("/objects/blank_heart", width, height);
    }
}

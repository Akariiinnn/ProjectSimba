package me.akariiinnn.objects;

import me.akariiinnn.entity.Entity;
import me.akariiinnn.main.GamePanel;

public class OBJ_HondFlag extends Entity {

    public OBJ_HondFlag(GamePanel gp) {

        super(gp);
        name = "Honduras Flag";
        this.width = gp.tileSize * 2;
        this.height = gp.tileSize * 3;

        solidArea.x = 0;
        solidArea.y = gp.tileSize*3;
        solidArea.width = gp.tileSize;
        solidArea.height = 5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down1 = setup("/objects/HondurasFlag", this.width, this.height);

    }

}

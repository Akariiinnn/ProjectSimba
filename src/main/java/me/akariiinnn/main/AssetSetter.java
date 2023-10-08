package me.akariiinnn.main;

import me.akariiinnn.entity.NPC_Walker;
import me.akariiinnn.objects.OBJ_Boots;
import me.akariiinnn.objects.OBJ_Door;
import me.akariiinnn.objects.OBJ_HondFlag;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_HondFlag(gp);
        gp.obj[0].worldX = gp.tileSize*18;
        gp.obj[0].worldY = gp.tileSize*17;

        gp.obj[1] = new OBJ_Boots(gp);
        gp.obj[1].worldX = gp.tileSize*23;
        gp.obj[1].worldY = gp.tileSize*18;

    }

    public void setNPC() {

        gp.npc[0] = new NPC_Walker(gp, "Lin", 1, gp.tileSize, gp.tileSize);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*19;
        gp.npc[0].collisionOn = true;
    }
}

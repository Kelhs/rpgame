package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "heart";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/heart_full", gp.tileSize, gp.tileSize);
    }
}

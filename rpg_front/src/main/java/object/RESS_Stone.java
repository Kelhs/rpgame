package object;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class RESS_Stone extends Entity {
    int id;
    public RESS_Stone(GamePanel gp, int id) {
        super(gp);
        this.id = id;

        down = new ArrayList<>();
        name = "Stone";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/stone", gp.tileSize, gp.tileSize);    
    }
    
}

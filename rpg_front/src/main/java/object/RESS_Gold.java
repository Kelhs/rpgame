package object;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class RESS_Gold extends Entity {
    int id;
    public RESS_Gold(GamePanel gp, int id) {
        super(gp);
        this.id = id;

        down = new ArrayList<>();
        name = "Gold";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/gold");
    }
    
}

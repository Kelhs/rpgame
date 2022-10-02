package object;

import entity.Entity;
import main.GamePanel;

import java.awt.Rectangle;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp){
        super(gp);

        name = "chest";
        solidArea = new Rectangle(0, 0, 80, 50);
        image = setup("objects/chest");
        // down.add(image);
        collision = true;
        
    }
}

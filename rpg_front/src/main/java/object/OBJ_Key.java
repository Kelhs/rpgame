package object;

import java.util.ArrayList;
import entity.Entity;
import main.GamePanel;
import java.awt.Rectangle;

public class OBJ_Key extends Entity {
    
    public OBJ_Key(GamePanel gp){
        super(gp);

        down = new ArrayList<>();
        name = "key";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/key", gp.tileSize, gp.tileSize);
        // down.add(image);
    }
}

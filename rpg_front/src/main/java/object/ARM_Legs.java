package object;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GamePanel;

public class ARM_Legs extends Stuff{
    
    public ARM_Legs(GamePanel gp, int id, String rarity) {
        super(gp, id, rarity);

        down = new ArrayList<>();
        name = "Legs";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/legs");
    }    

    
}

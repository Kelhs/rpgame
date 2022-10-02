package object;

import java.awt.Rectangle;

import main.GamePanel;

public class WEA_Sword extends Stuff {
    
    public WEA_Sword(GamePanel gp, int id, String rarity) {
        super(gp, id, rarity);

        name = "Sword";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/sword");
    }
 
}

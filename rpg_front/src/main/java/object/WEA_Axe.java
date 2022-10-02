package object;

import java.awt.Rectangle;

import main.GamePanel;

public class WEA_Axe extends Stuff {
    
    public WEA_Axe(GamePanel gp, int id, String rarity) {
        super(gp, id, rarity);

        name = "Axe";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/axe");
    }
    
}

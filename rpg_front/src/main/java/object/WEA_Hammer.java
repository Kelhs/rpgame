package object;

import main.GamePanel;

import java.awt.Rectangle;

public class WEA_Hammer extends Stuff {
    
    public WEA_Hammer(GamePanel gp, int id, String rarity) {
        super(gp, id, rarity);

        name = "Hammer";
        solidArea = new Rectangle(0, 0, 80, 80);
        image = setup("objects/hammer");
    }

    
}

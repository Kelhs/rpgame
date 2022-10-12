package object;

import entity.Entity;
import main.GamePanel;

public class Stuff extends Entity{
    public String rarity;
    public int id;
    public Stuff(GamePanel gp, int id, String rarity) {
        super(gp);

        this.id = id;
        this.rarity = rarity;
         type = 3;

        numberOfImage = 0;
    }
    
}

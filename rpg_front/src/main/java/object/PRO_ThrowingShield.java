package object;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Projectile;
import main.GamePanel;

public class PRO_ThrowingShield extends Projectile {

    
    public PRO_ThrowingShield(GamePanel gp) {
        super(gp);

        name = "Throwing Shield";
        speed = 8;
        attack = 80;
        delayBetweenShots = 10;
        maxHealth = 160;
        health = maxHealth;
        alive = false;
        up = new ArrayList<>();
        down = new ArrayList<>();
        left = new ArrayList<>();
        right = new ArrayList<>();

        solidArea = new Rectangle(0, 0, 80, 80);

        
        getImage(gp);
    } 

    public void getImage(GamePanel gp){
        up.add(setup("objects/shield_wood", gp.tileSize/2, gp.tileSize/2));
        down.add(setup("objects/shield_wood", gp.tileSize/2, gp.tileSize/2));
        left.add(setup("objects/shield_wood", gp.tileSize/2, gp.tileSize/2));
        right.add(setup("objects/shield_wood", gp.tileSize/2, gp.tileSize/2));
    }
    
}

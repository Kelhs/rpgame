package monster;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity{

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        name = "Green Slime";
        type = 2;
        speed = 1;
        maxHealth = 40;
        health = maxHealth;
        defense = 0;
        attack = 5;
        animationSpeed = 8;
        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 25;
        solidArea.width = 70;
        solidArea.height = 55;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        numberOfImage = 1;

        up = new ArrayList<>();
        down = new ArrayList<>();
        left = new ArrayList<>();
        right = new ArrayList<>();

        getImage();
    }

    public void getImage(){
        for(int i = 1; i <= 2; i++){
            up.add(setup("monster/greenslime_down_" + i));
            down.add(setup("monster/greenslime_down_" + i));
            left.add(setup("monster/greenslime_down_" + i));
            right.add(setup("monster/greenslime_down_" + i));
        }
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    
}

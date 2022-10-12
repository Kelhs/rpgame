package entity;

import main.GamePanel;

public class Projectile extends Entity{

    int startYPosition;
    int startXPosition;
    boolean go = true;
    boolean back;
    Entity user;
    public int delayBetweenShots;

    public Projectile(GamePanel gp) {
        super(gp);

        
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        switch(direction){
            case "up":
                startYPosition = gp.player.worldY - gp.tileSize/2;
                startXPosition = gp.player.worldX;
                break;
            case "down":
                startYPosition = gp.player.worldY + gp.tileSize/2;
                startXPosition = gp.player.worldX;
                break;
            case "left":
                startYPosition = gp.player.worldY;
                startXPosition = gp.player.worldX - gp.tileSize/2;
                break;
            case "right":
                startYPosition = gp.player.worldY;
                startXPosition = gp.player.worldX + gp.tileSize/2;

        }
        
        this.worldX = startXPosition;
        this.worldY = startYPosition;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
    }

    public void update(){
        
        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, this);
            }
        }
        if(user != gp.player){

        }

        switch(direction){
            case "up":
                if(go){
                    back = false;
                    worldY -= speed;
                    if(worldY <= startYPosition - gp.tileSize * 5){
                        go = false;
                        back = true;
                    }
                }
                if(back){
                    worldY += speed;
                    if(worldY >= startYPosition){
                        alive = false;
                        go = true;
                    }
                }
                break;
            case "down":
                if(go){
                    back = false;
                    worldY += speed;
                    if(worldY >= startYPosition + gp.tileSize * 5){
                        go = false;
                        back = true;
                    }
                }
                if(back){
                    worldY -= speed;
                    if(worldY <= startYPosition){
                        alive = false;
                        go =true;
                    }
                }
                break;
            case "left":
                if(go){
                    back = false;
                    worldX -= speed;
                    if(worldX <= startXPosition - gp.tileSize * 5){
                        go = false;
                        back = true;
                    }
                }
                if(back){
                    worldX += speed;
                    if(worldX >= startXPosition){
                        alive = false;
                        go = true;
                    }
                }
                break;
            case "right":
                if(go){
                    back = false;
                    worldX += speed;
                    if(worldX >= startXPosition + gp.tileSize * 5){
                        go = false;
                        back = true;
                    }
                }
                if(back){
                    worldX -= speed;
                    if(worldX <= startXPosition){
                        alive = false;
                        go = true;
                    }
                }
                break;
        }
    }
    
}

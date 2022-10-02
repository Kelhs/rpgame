package entity;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;

import main.GamePanel;
import main.UtilityTool;
import object.ARM_Boots;
import object.ARM_Head;
import object.ARM_Legs;
import object.RESS_Gold;
import object.RESS_Stone;
import object.RESS_Wood;
import object.WEA_Axe;
import object.WEA_Hammer;
import object.WEA_Sword;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Entity {
    public int worldX, worldY;

    public ArrayList<BufferedImage> up, down, left, right = new ArrayList<>();
    public ArrayList<BufferedImage> attackUp, attackDown, attackLeft, attackRight = new ArrayList<>();
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int numberOfImage;
    public int animationSpeed;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public boolean invincible = false;
    public int invincibleCounter;

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public boolean gotInteract = false;
    public boolean dying = false;
    public int type; // 0 = player; 1 = npc; 2 = monster;
    public int actionLockCounter;

    public int attack, strength, agility, intelligence, speed, health, maxHealth, defense, luck, attackSpeed;

    public Projectile projectile;
    public boolean alive = true;



    GamePanel gp;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}

    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        // gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(collisionOn == false){
            switch(direction){
                case "up":
                     worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                     worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter == animationSpeed){
            spriteNum ++;
            if(spriteNum > numberOfImage){
                spriteNum = 0;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter ++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        } 
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up.get(spriteNum);
                break;
            case "down":
                image = down.get(spriteNum);
                break;
            case "left":
                image = left.get(spriteNum);
                break;
            case "right":
                image = right.get(spriteNum);
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void drawObject(Graphics2D g2, GamePanel gp, BufferedImage objectImage){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(objectImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void drawLoot(Graphics2D g2){
        BufferedImage image = null;
        image = down.get(spriteNum);
        g2.drawImage(image, this.worldX, this.worldY, gp.tileSize, gp.tileSize, null);
    }

    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/assets/" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public JSONObject setLootOnMonsterKill() throws IOException{

        URL urlForGetRequest = new URL("http://localhost:9090/Randomdrop/" + gp.player.luck);
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            JSONObject json = new JSONObject(response.toString());
            return json;
        } else {
            System.out.println("GET NOT WORKED");
            return null;
        }
         
}

    public void checkDrop(){
        int iRandom = new Random().nextInt(100);
        Entity lootCreated = null;
        if(iRandom < 10){
            //DROP KEY
        } else if(iRandom >= 10 && iRandom < 30){
            //DROP HEALTH
        } else if(iRandom >= 30 && iRandom < 50){
            //DROP NOTHING
        } else {
            JSONObject loot = null;
            try {
                loot = setLootOnMonsterKill();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String lootCategorie = loot.getString("categorie");
            if(lootCategorie.equals("Weapon") || lootCategorie.equals("Armor")){
                String lootType = loot.getString("type");
                String lootRarity = loot.getString("rarity");
                int lootId = loot.getInt("id");
                switch(lootType){
                    case "Sword":
                        lootCreated = new WEA_Sword(gp, lootId, lootRarity);
                        break;
                    case "Hammer":
                        lootCreated = new WEA_Hammer(gp, lootId, lootRarity);
                        break;
                    case "Axe":
                        lootCreated = new WEA_Axe(gp, lootId, lootRarity);
                        break;
                    case "Head":
                        lootCreated = new ARM_Head(gp, lootId, lootRarity);
                        break;
                    case "Legs":
                        lootCreated = new ARM_Legs(gp, lootId, lootRarity);
                        break;
                    case "Boots":
                        lootCreated = new ARM_Boots(gp, lootId, lootRarity);
                        break;
                }
                dropItem(lootCreated);

            }
            if(lootCategorie.equals("Ressources")){
                String lootType = loot.getString("type");
                int lootId = loot.getInt("id");
                switch(lootType){
                    case "Gold":
                        lootCreated = new RESS_Gold(gp, lootId);
                        break;
                    case "Stone":
                        lootCreated = new RESS_Stone(gp, lootId);
                        break;
                    case "Wood":
                        lootCreated = new RESS_Wood(gp, lootId);
                        break;
                }
                dropItem(lootCreated);
            } 
            if (lootCategorie.equals("noLoot")){
            }
        }
    }

    public void dropItem(Entity droppedItem){
            droppedItem.worldX = worldX;
            droppedItem.worldY = worldY;
            gp.obj.add(droppedItem);
            
        
    }
}

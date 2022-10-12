package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;

import java.awt.Rectangle;

import main.KeyHandler;
import object.PRO_ThrowingShield;
import main.GamePanel;

public class Player extends Entity {
    KeyHandler keyH;
    int invinciblePlayerCounter;
    public final int screenX;
    public final int screenY;
    public int hasKey;
    public int amountOfGold;
    public int amountOfWood;
    public int amountOfStone;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        down = new ArrayList<>();
        up = new ArrayList<>();
        left = new ArrayList<>();
        right = new ArrayList<>();
        solidArea = new Rectangle();
        solidArea.x = 20;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 40;
        solidArea.height = 60;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        for (int i = 0; i <= 14; i++) {
            down.add(setup("player/front/warrior" + i, gp.tileSize, gp.tileSize));
            up.add(setup("player/back/warrior" + i, gp.tileSize, gp.tileSize));
            left.add(setup("player/left/warrior" + i, gp.tileSize, gp.tileSize));
            right.add(setup("player/right/warrior" + i, gp.tileSize, gp.tileSize));
        }
    }

    public void setDefaultValues() {
        try {
            JSONObject stats = getPlayerStats();
            speed = stats.getInt("speed");
            luck = stats.getInt("luck");
            strength = stats.getInt("strength");
            agility = stats.getInt("agility");
            intelligence = stats.getInt("intelligence");
            stamina = stats.getInt("stamina");
            defense = stats.getInt("defense");
            level = stats.getInt("levelExp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        worldX = gp.tileSize * (gp.maxWorldCol / 2);
        worldY = gp.tileSize * (gp.maxWorldRow / 2);
        maxHealth = 100 + (stamina * 5);
        health = maxHealth;
        numberOfImage = 14;
        attack = strength * 2;
        attackSpeed = agility * 2;
        projectile = new PRO_ThrowingShield(gp);

    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
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
            if (spriteCounter == 3) {
                spriteNum++;
                if (spriteNum > 14) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }
        if (invincible) {
            invinciblePlayerCounter++;
            if (invinciblePlayerCounter > 60) {
                invincible = false;
                invinciblePlayerCounter = 0;
            }
        }

        // CHECK MONSTER COLLISION
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        if (!projectile.alive) {
            projectile.set(this.worldX, this.worldY, this.direction, true, this);
            gp.projectileList.add(projectile);
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj.get(index).name;

            switch (objectName) {
                case "key":
                    hasKey++;
                    gp.obj.get(index).gotInteract = true;
                    if (gp.obj.get(index).gotInteract) {
                        gp.obj.remove(index);
                    }
                    gp.ui.showMessage("Key picked up");
                    break;
                case "chest":
                    collisionOn = true;
                    if (hasKey > 0) {
                        if (!gp.obj.get(index).gotInteract) {
                            gp.ui.showMessage("Key used");
                            gp.obj.get(index).gotInteract = true;
                            gp.obj.get(index).image = setup("objects/chest_opened", gp.tileSize, gp.tileSize);
                            hasKey--;
                        }

                    } else {
                        if (!gp.obj.get(index).gotInteract) {
                            gp.ui.showMessage("Key is needed");
                        }
                    }
                    break;
                case "heart":
                    gp.obj.get(index).gotInteract = true;
                    if (health < maxHealth) {
                        health += 10;
                        if (health > maxHealth) {
                            health = maxHealth;
                        }
                    }
                    if (gp.obj.get(index).gotInteract) {
                        gp.obj.remove(index);
                    }
                    break;
                case "Gold":
                    gp.obj.get(index).gotInteract = true;
                    if (gp.obj.get(index).gotInteract) {
                        amountOfGold++;
                        gp.obj.remove(index);
                    }
                    break;
                case "Wood":
                    gp.obj.get(index).gotInteract = true;
                    if (gp.obj.get(index).gotInteract) {
                        amountOfWood++;
                        gp.obj.remove(index);
                    }
                    break;
                case "Stone":
                    gp.obj.get(index).gotInteract = true;
                    if (gp.obj.get(index).gotInteract) {
                        amountOfStone++;
                        gp.obj.remove(index);
                    }
                    break;
                default:
                    gp.obj.get(index).gotInteract = true;
                    if (gp.obj.get(index).gotInteract) {
                        gp.obj.remove(index);
                    }
                    break;
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                double damage = gp.monster.get(i).attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                health -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, Entity e) {
        if (i != 999) {
            if (!gp.monster.get(i).invincible) {
                double damage = e.attack - gp.monster.get(i).defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster.get(i).health -= damage;
                gp.monster.get(i).invincible = true;
                // gp.monster.get(i).damageReaction();
                if (gp.monster.get(i).health <= 0) {
                    gp.monster.get(i).dying = true;
                    gp.monster.get(i).alive = false;
                }
            }
        }
    }

    public JSONObject getPlayerStats() throws IOException {
        URL urlForGetRequest = new URL("http://localhost:9090/Stats/1");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            JSONObject json = new JSONObject(response.toString());
            return json;
        } else {
            System.out.println("GET NOT WORKED");
            return null;
        }

    }
}

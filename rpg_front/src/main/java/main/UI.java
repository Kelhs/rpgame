package main;

import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.OBJ_Key;
import object.RESS_Gold;
import object.RESS_Stone;
import object.RESS_Wood;
import object.Stuff;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;  

    Font arial_25;
    Font arial_60;
    BufferedImage keyImage, woodImage, goldImage, stoneImage;
    public boolean messageOn = false;
    public String message = "";
    public String previousMessage = "";
    public double playTime;
    int messageCounter = 0;
    int spriteCounter = 0;
    int spriteNum = 0;
    DecimalFormat dFormat = new DecimalFormat("#0");
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_60 = new Font("Arial", Font.PLAIN, 60);

        OBJ_Key key = new OBJ_Key(gp);
        RESS_Wood wood = new RESS_Wood(gp, 0);
        RESS_Gold gold = new RESS_Gold(gp, 0);
        RESS_Stone stone = new RESS_Stone(gp, 0);

        keyImage = key.image;
        woodImage = wood.image;
        goldImage = gold.image;
        stoneImage = stone.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        
        this.g2 = g2;

        g2.setFont(arial_60);
        g2.setColor(Color.white);
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            //MESSAGE
            g2.setFont(arial_25);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 0, -30, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 65, 45);

            g2.drawImage(goldImage, 0, 50, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.amountOfGold, 65, 120);

            g2.drawImage(woodImage, 0, 130, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.amountOfWood, 65, 200);

            g2.drawImage(stoneImage, 0, 210, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.amountOfStone, 65, 280);

            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 20));
                g2.drawString(message, gp.player.screenX + (gp.tileSize + 10), gp.player.screenY + (gp.tileSize/2 + 5));
                if(previousMessage != message){
                    messageCounter = 0;
                }
                previousMessage = message;
                messageCounter ++;
                if(messageCounter == 90){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

            //TIME
            playTime += (double) 1/60;
            g2.drawString("" + dFormat.format(playTime), (gp.maxScreenCol * gp.tileSize)/2 - (gp.tileSize/2), 30);

            //DRAW PLAYER HEALTH
            drawPlayerHealth(gp.player.health, gp.player.maxHealth);


            //DRAW RARITY ITEM
            for(int i = 0; i < gp.obj.size(); i++){
                int x = 0;
                int y = 0;
                if(gp.obj.get(i) != null){
                    if(gp.obj.get(i).type == 3){
                        if(gp.obj.get(i).worldX - gp.player.worldX < 0){
                            x = gp.screenWidth/2 - (gp.player.worldX - gp.obj.get(i).worldX);
                        }
                        if(gp.player.worldX - gp.obj.get(i).worldX <= 0){
                            x = gp.screenWidth/2 + (gp.obj.get(i).worldX - gp.player.worldX);
                        }
                        if(gp.obj.get(i).worldY - gp.player.worldY < 0){
                            y = gp.screenHeight/2 - (gp.player.worldY - gp.obj.get(i).worldY);
                        }
                        if (gp.player.worldY - gp.obj.get(i).worldY <= 0){
                            y = gp.screenHeight/2 + (gp.obj.get(i).worldY - gp.player.worldY);
                        }

                        if(x > gp.screenWidth){
                            x = 10000;
                        }
                        if(y > gp.screenHeight){
                            y = 10000;
                        }
                        Stuff stuff = (Stuff) gp.obj.get(i);
                        if (x == 10000 || y == 1000){
                            //DONT DISPLAY AURA OUT OF SCREEN
                        }else{
                            drawRarityAura(stuff.rarity,x - gp.tileSize/3, y - gp.tileSize/2, stuff.name);
                        }
                    }
                }
            }
        }

        //DRAW STATE PLAYER
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventoryScreen();
        }
        
    }
    public void drawInventoryScreen(){
        //FRAME
        final int frameX = gp.tileSize * (gp.maxScreenCol/2 + 1);
        final int frameY = gp.tileSize * 1;
        final int frameWidth = gp.tileSize * 10;
        final int frameHeight = gp.tileSize * 11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        //CURSOR
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 20, 20);

    }

    public void drawCharacterScreen(){
        //CREATE A FRAME
        final int frameX = gp.tileSize * 1;
        final int frameY = gp.tileSize * 1;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //DISPLAY STATS
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 48;
        drawStats(textX, textY, lineHeight);
        drawItemSlot();
    }

    public void drawStats(int x, int y, int lineHeight){
        g2.drawString("Level : " + gp.player.level, x, y);
        y += lineHeight;
        g2.drawString("Stamina : " + gp.player.stamina, x, y);
        y += lineHeight;
        g2.drawString("Strength : " + gp.player.strength, x, y);
        y += lineHeight;
        g2.drawString("Agility : " + gp.player.agility, x, y);
        y += lineHeight;
        g2.drawString("Intelligence : " + gp.player.intelligence, x, y);
        y += lineHeight;
        g2.drawString("Speed : " + gp.player.speed, x, y);
        y += lineHeight;
        g2.drawString("Defense : " + gp.player.defense, x, y);
        y += lineHeight;
        g2.drawString("Luck : " + gp.player.luck, x, y);
        y += lineHeight;
        g2.drawString("Attack speed : " + gp.player.attackSpeed, x, y);
        y += lineHeight;
        g2.drawString("Max health : " + gp.player.maxHealth, x, y);
        y += lineHeight;
        g2.drawString("Current health : " + gp.player.health, x, y);
    }

    public void drawItemSlot(){
        g2.setColor(Color.white);

        BufferedImage image = null;

        if(gp.player.currentHead != null){
            image = gp.player.currentHead.image;
        } else if(gp.player.currentHead == null){
            image = null;
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(gp.tileSize * 2, gp.tileSize * 9, gp.tileSize, gp.tileSize, 20, 20);
        g2.drawImage(image, gp.tileSize, gp.tileSize * 9, null);

        if(gp.player.currentLegs != null){
            image = gp.player.currentLegs.image;
        } else if(gp.player.currentLegs == null){
            image = null;
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(gp.tileSize * 3 + (gp.tileSize/2), gp.tileSize * 9, gp.tileSize, gp.tileSize, 20, 20);
        g2.drawImage(image, gp.tileSize * 2, gp.tileSize * 9, null);

        if(gp.player.currentBoots != null){
            image = gp.player.currentBoots.image;
        } else if(gp.player.currentBoots == null){
            image = null;
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(gp.tileSize * 5, gp.tileSize * 9, gp.tileSize, gp.tileSize, 20, 20);
        g2.drawImage(image, gp.tileSize * 5, gp.tileSize * 9, null);

        if(gp.player.currentWeapon != null){
            image = gp.player.currentWeapon.image;
        } else if(gp.player.currentWeapon == null){
            image = null;
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(gp.tileSize * 3 + (gp.tileSize/2), gp.tileSize * 10 + (gp.tileSize/2), gp.tileSize, gp.tileSize, 20, 20);
        g2.drawImage(image, gp.tileSize * 5, gp.tileSize * 10 + (gp.tileSize/2), null);
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 40, 40);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 30, 30);
    }

    public void drawPauseScreen(Graphics2D g2){
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2 ;
        return x;
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if(titleScreenState == 0){

            //TITLENAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
            String text = "Did I die again?";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //DISPLAY CHARACTER
            x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
            y += gp.tileSize;
            spriteCounter++;
                if(spriteCounter == 3){
                    spriteNum ++;
                    if(spriteNum > 14){
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            g2.drawImage(gp.player.down.get(spriteNum), x, y, gp.tileSize*2, gp.tileSize*2, null);
            
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
            BufferedImage image = null;
            UtilityTool uTool = new UtilityTool();
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/assets/objects/sword_normal.png"));
                image = uTool.scaleImage(image, gp.tileSize/2, gp.tileSize/2);

            }catch(IOException e){
                e.printStackTrace();
            }
            //NEW
            text = "NEW";
            x = getXForCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawImage(image, x - gp.tileSize/2, y - gp.tileSize/2, null);
            }

            //LOAD
            text = "LOAD";
            x = getXForCenteredText(text);
            y += gp.tileSize * 1;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawImage(image, x - gp.tileSize/2, y - gp.tileSize/2, null);
            }

            //OPTIONS
            text = "OPTIONS";
            x = getXForCenteredText(text);
            y += gp.tileSize * 1;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawImage(image, x - gp.tileSize/2, y - gp.tileSize/2, null);
            }

            //QUIT
            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize * 1;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawImage(image, x - gp.tileSize/2, y - gp.tileSize/2, null);
            }
        }

        //CLASS SELECTION
        if(titleScreenState == 1){
            BufferedImage image = null;
            UtilityTool uTool = new UtilityTool();
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/assets/player/front/warrior0.png"));
                image = uTool.scaleImage(image, gp.tileSize*3, gp.tileSize*3);

            }catch(IOException e){
                e.printStackTrace();
            }
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*3;

            g2.drawString(text, x, y);

            //KNIGHT
            text = "Knight";
            x = gp.tileSize * 4;
            y += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize + 20, y);
                g2.drawString("Spells:", gp.tileSize, 40);
                g2.drawString("-Throwing Shield:", gp.tileSize, 80);
                g2.drawString("Throw your shield ", gp.tileSize, 120);
                g2.drawString(" in front of you", gp.tileSize, 160);
                g2.drawString(" and come back to", gp.tileSize, 200);
                g2.drawString(" your position.", gp.tileSize, 240);
            }

            //MAGE
            text = "Mage";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize + 20, y);
                g2.drawString("Spells:", gp.tileSize, 40);
                g2.drawString("-Fireball:", gp.tileSize, 80);
                g2.drawString("Throw a fireball", gp.tileSize, 120);
                g2.drawString(" in front of you", gp.tileSize, 160);
            }

            //ARCHER
            text = "Archer";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize + 20, y);
                g2.drawString("Spells:", gp.tileSize, 40);
                g2.drawString("-Piercing shot", gp.tileSize, 80);
                g2.drawString("Shot an arrow ", gp.tileSize, 120);
                g2.drawString(" in front of you.", gp.tileSize, 160);
                g2.drawString(" It pass through enemies", gp.tileSize, 200);
            }

            //DRUD
            text = "Drud";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tileSize + 20, y);
                g2.drawString("Spells:", gp.tileSize, 40);
                g2.drawString("-Throwing Shield:", gp.tileSize, 80);
                g2.drawString("Throw your shield ", gp.tileSize, 120);
                g2.drawString(" in front of you", gp.tileSize, 160);
            }

            //BACK
            text = "BACK";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;

            g2.drawString(text, x, y);
            if(commandNum == 4){
                g2.drawString(">", x - gp.tileSize + 20, y);
            }
        }
    }

    public void drawPlayerHealth(int health, int maxHealth){
        int currentHealth = health * 6;
        int missingHealth = maxHealth * 6 - currentHealth;
        g2.setColor(Color.red);
        g2.fillRect(gp.screenWidth/3, gp.screenHeight - 60, currentHealth, 20);

        g2.setColor(Color.white);
        g2.fillRect((gp.screenWidth/3) + currentHealth, gp.screenHeight - 60, missingHealth, 20);
    }

    public void drawRarityAura(String rarity, int worldX, int worldY, String lootName){
        switch(rarity){
            case "Common":
                g2.setPaint(Color.white);
                break;
            case "Uncommon":
                g2.setPaint(Color.green);
                break;
            case "Rare":
                g2.setPaint(Color.blue);
                break;
            case "Epic":
                g2.setPaint(new Color(150, 0, 150));
                break;
            case "Legendary":
                g2.setPaint(Color.yellow);
                break;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.30F));
        switch(lootName){
            case "Legs":
                g2.fillOval(worldX, worldY + 30, 50, 60);
                break;
            case "Boots":
                g2.fillOval(worldX, worldY + 45, 50, 50);
                break;
            case "Head":
                g2.fillOval(worldX, worldY + 40, 50, 50);
                break;
            case "Axe":        
                g2.fillOval(worldX, worldY + 20, 50, 70);
                break;
            case "Sword":
                g2.fillOval(worldX, worldY + 10 , 50, 80);
                break;
            case "Hammer":
                g2.fillOval(worldX, worldY + 20, 50, 70);
                break;
        }
    }

}

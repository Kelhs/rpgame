package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;  

    Font arial_25;
    Font arial_60;
    BufferedImage keyImage;
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

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_60 = new Font("Arial", Font.PLAIN, 60);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
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
            g2.drawImage(keyImage, -10, -30, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 55, 45);

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
        }
        
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

            }

            //MAGE
            text = "Mage";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize + 20, y);

            }

            //ARCHER
            text = "Archer";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize + 20, y);

            }

            //DRUD
            text = "Drud";
            x += gp.tileSize * 5;

            g2.drawImage(image, x - 60, y - gp.tileSize*4, null);
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tileSize + 20, y);

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

}

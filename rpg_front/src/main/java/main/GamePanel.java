package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN STINGS
    final int originalTileSize = 16; // 32x32 tile
    final int scale = 5; 

    public final int tileSize = originalTileSize * scale; // 80x80 tile
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol; // 1920px
    public final int screenHeight = tileSize * maxScreenRow; // 1024px


    // World settings
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 80;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //TIME
    double drawInterval = 1000000000/fps;
    public double nextDrawTime = System.nanoTime() + drawInterval;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public ArrayList<Entity> loot = new ArrayList<>();
    public ArrayList<Entity> obj = new ArrayList<>();
    public ArrayList<Entity> monster = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        

         while(gameThread != null){
            //Update info
            update();
            //Draw the screen with updated info
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
         }
    }

    public void update(){

        if(gameState == playState){
            player.update();
            aSetter.setMonster();

            //Monster
            for(int i = 0; i < monster.size(); i++){
                if(monster.get(i) != null){
                    if(monster.get(i).alive){
                        monster.get(i).update();
                    }
                    if(!monster.get(i).alive){
                        monster.get(i).checkDrop();
                        monster.remove(i);
                    }
                }
            }
            //PROJECTILE
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    projectileList.get(i).update();
                }
                if(projectileList.get(i).alive == false){
                    projectileList.remove(i);
                }
            }
            // //Loot 
            // for(int i = 0; i < loot.size(); i++){
            //     if(loot.get(i) != null){
            //         loot.get(i).update();
            //     }
            // }
        }
        if(gameState == pauseState){
            //none
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //TTLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        } else {
            tileM.draw(g2);

            entityList.add(player);

            for(int i = 0; i < obj.size(); i++){
                if(obj.get(i) != null){
                    System.out.println(obj.get(i));
                    obj.get(i).drawObject(g2, this, obj.get(i).image);
                }
            }

            for(int i = 0; i < monster.size(); i++){
                if(monster.get(i) != null){
                    entityList.add(monster.get(i));
                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }
            
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
                
            });

            //DRAW LOOT
            for(int i = 0; i < loot.size(); i++){
                loot.get(i).drawLoot(g2);
            }

            //DRAW ENTITY
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            //empty entitylist
            entityList.clear();
            
            ui.draw(g2);

            if(gameState == pauseState){
                ui.drawPauseScreen(g2);
            }
        }
        g2.dispose();

    }
}

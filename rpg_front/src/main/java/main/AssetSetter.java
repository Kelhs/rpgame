package main;

import java.util.Random;

import entity.Entity;
import monster.MON_GreenSlime;


public class AssetSetter {
    
    GamePanel gp;

    int timeMultiplicator = 0;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        // Entity chest = new OBJ_Chest(gp);
        // chest.worldX = 20 * gp.tileSize;
        // chest.worldY = 20 * gp.tileSize;
        // chest.name = "chest";
        // gp.obj.add(chest);

        // Entity chest2 = new OBJ_Chest(gp);
        // chest2.worldX = 25 * gp.tileSize;
        // chest2.worldY = 25 * gp.tileSize;
        // chest2.name = "chest";
        // gp.obj.add(chest2);


        // gp.obj[2] = new OBJ_Key(gp);
        // gp.obj[2].worldX = 25 * gp.tileSize;
        // gp.obj[2].worldY = 25 * gp.tileSize;
        // gp.obj[2].name = "key";


        // gp.obj[3] = new OBJ_Key(gp);
        // gp.obj[3].worldX = 35 * gp.tileSize;
        // gp.obj[3].worldY = 35 * gp.tileSize;
        // gp.obj[3].name = "key";

    }

    public void setMonster(){
        if(Integer.parseInt(gp.ui.dFormat.format(gp.ui.playTime)) == 15 * timeMultiplicator){
            Entity monsterSpawn[] = new Entity[1000];
            timeMultiplicator++;
            for(int i = 0; i < 5 * timeMultiplicator; i++){
                Random random = new Random();
                int sideRandom = random.nextInt(8) + 1;
                int sideHeightRandomPosition = random.nextInt(6);
                int sideWidthRandomPosition = random.nextInt(10);
                monsterSpawn[i] = new MON_GreenSlime(gp);
                switch(sideRandom){
                    case 1:
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * 10;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * sideHeightRandomPosition;
                        break;
                    case 2:
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * 10;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * sideHeightRandomPosition;
                        break;
                    case 3:
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * 10;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * sideHeightRandomPosition;
                        break;
                    case 4:
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * 10;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * sideHeightRandomPosition;
                        break;
                    case 5:
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * sideWidthRandomPosition;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * 6;
                        break;
                    case 6:
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * sideWidthRandomPosition;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * 6;
                        break;
                    case 7:
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * sideWidthRandomPosition;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * 6;
                        break;
                    case 8:
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * sideWidthRandomPosition;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * 6;
                        break;
                }
            }
            for(int i = 0; i < monsterSpawn.length; i ++){
                gp.monster.add(monsterSpawn[i]);
            }
        }
    }
}

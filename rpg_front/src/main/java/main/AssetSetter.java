package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.JSONObject;

import entity.Entity;
import monster.MON_GreenSlime;
import object.ARM_Boots;
import object.ARM_Head;
import object.ARM_Legs;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.WEA_Axe;
import object.WEA_Hammer;
import object.WEA_Sword;

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
                int iRandom = random.nextInt(8) + 1;
                monsterSpawn[i] = new MON_GreenSlime(gp);

                switch(iRandom){
                    case 1 :
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * 5;
                        monsterSpawn[i].worldY = gp.player.worldY;
                        break;
                    case 2 :
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * 5;
                        monsterSpawn[i].worldY = gp.player.worldY;
                        break;
                    case 3 :
                        monsterSpawn[i].worldX = gp.player.worldX;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * 5;
                        break;
                    case 4:
                        monsterSpawn[i].worldX = gp.player.worldX;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * 5;
                        break;
                    case 5 :
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * 4;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * 2;
                        break;
                    case 6 :
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * 4;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * 2;
                        break;
                    case 7 :
                        monsterSpawn[i].worldX = gp.player.worldX + gp.tileSize * 2;
                        monsterSpawn[i].worldY = gp.player.worldY + gp.tileSize * 5;
                        break;
                    case 8:
                        monsterSpawn[i].worldX = gp.player.worldX - gp.tileSize * 2;
                        monsterSpawn[i].worldY = gp.player.worldY - gp.tileSize * 5;
                        break;
                }
            }
            for(int i = 0; i < monsterSpawn.length; i ++){
                gp.monster.add(monsterSpawn[i]);
            }
        }
    }

    // public JSONObject setLootOnMonsterKill() throws IOException{

    //         URL urlForGetRequest = new URL("http://localhost:9090/Randomdrop/" + gp.player.luck);
    //         String readLine = null;
    //         HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
    //         connection.setRequestMethod("POST");
    //         int responseCode = connection.getResponseCode();
    //         if (responseCode == HttpURLConnection.HTTP_OK) {
    //             BufferedReader in = new BufferedReader(
    //                 new InputStreamReader(connection.getInputStream()));
    //             StringBuilder response = new StringBuilder();
    //             while ((readLine = in .readLine()) != null) {
    //                 response.append(readLine);
    //             } in .close();
    //             JSONObject json = new JSONObject(response.toString());
    //             return json;
    //         } else {
    //             System.out.println("GET NOT WORKED");
    //             return null;
    //         }
             
    // }

    // public void setDropForThePlayer(int worldX, int worldY){
    //     JSONObject loot = null;
    //     try {
    //         loot = setLootOnMonsterKill();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     String lootCategorie = loot.getString("categorie");
    //     Entity lootCreated = null;
    //     if(lootCategorie.equals("Weapon") || lootCategorie.equals("Armor")){
    //         String lootType = loot.getString("type");
    //         String lootRarity = loot.getString("rarity");
    //         int lootId = loot.getInt("id");
    //         switch(lootType){
    //             case "Sword":
    //                 lootCreated = new WEA_Sword(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //             case "Hammer":
    //                 lootCreated = new WEA_Hammer(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //             case "Axe":
    //                 lootCreated = new WEA_Axe(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //             case "Head":
    //                 lootCreated = new ARM_Head(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //             case "Legs":
    //                 lootCreated = new ARM_Legs(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //             case "Boots":
    //                 lootCreated = new ARM_Boots(gp, lootId, lootRarity, worldX, worldY);
    //                 break;
    //         }
    //         gp.loot.add(lootCreated);
    //     }
    //     if(lootCategorie.equals("Ressources")){
    //         String lootType = loot.getString("type");
    //         switch(lootType){
    //             case "Gold":
    //                 break;
    //             case "Stone":
    //                 break;
    //             case "Wood":
    //                 break;
    //         }
    //     } 
    //     if (lootCategorie.equals("noLoot")){
    //     }
        

    // }
}

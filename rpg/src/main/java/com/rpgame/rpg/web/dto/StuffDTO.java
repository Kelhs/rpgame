package com.rpgame.rpg.web.dto;

import com.rpgame.rpg.model.Armor;
import com.rpgame.rpg.model.Loot;
import com.rpgame.rpg.model.Stuff;
import com.rpgame.rpg.model.Weapon;

public class StuffDTO {
    private String categorie;
    private String rarity;
    private String type;

    /**
     * @return CategorieENUM return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return String return the rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * @param rarity the rarity to set
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public static Stuff StuffDTO2Stuff(Loot loot, String rarity, String categorie) {
        Stuff s = new Armor();
        switch (categorie) {
            case "Armor":
                s = new Armor(loot, rarity, categorie);
                break;
            case "Weapon":
                s = new Weapon(loot, rarity, categorie);
                break;
        }
        return s;
    }

}

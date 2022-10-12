package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Armor extends Stuff {

    public Armor() {

    }

    public Armor(Loot loot, String rarity, String categorie) {
        super(loot, rarity);
        this.categorie = categorie;
    }

    @Override
    public void setType() {
        int maxPossibilities = 3;
        double randomArmor = Math.floor((Math.random() * maxPossibilities) + 1);
        if (randomArmor == 1) {
            this.type = "Head";
        } else if (randomArmor == 2) {
            this.type = "Legs";
        } else if (randomArmor == 3) {
            this.type = "Boots";
        } else {
            this.type = "";
        }
    }

}

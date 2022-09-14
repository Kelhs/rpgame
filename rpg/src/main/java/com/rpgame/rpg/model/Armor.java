package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Armor extends Stuff {

    @Id
    @GeneratedValue
    private int id;
    
    public Armor(Loot loot, String rarity){
        super(loot, rarity);    
    }

    @Override
    public void setType(){
        int maxPossibilities = 3;
        double randomArmor = Math.floor((Math.random() * maxPossibilities) + 1);
        if(randomArmor == 1){
            this.type = "Head";
        } else if(randomArmor == 2){
            this.type = "Legs";
        } else if(randomArmor == 3){
            this.type = "Boots";
        } else{
            this.type = "";
        }
    }

}

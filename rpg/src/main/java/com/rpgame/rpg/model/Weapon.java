package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Weapon extends Stuff {

    @Id
    @GeneratedValue
    private int id;
    
    public Weapon(Loot loot, String rarity){   
        super(loot, rarity);
    }

    @Override
    public void setType(){
        int maxPossibilities = 3;
        double randomWeapon = Math.floor((Math.random() * maxPossibilities) + 1);
        if(randomWeapon == 1){
            this.type = "Axe";
        } else if(randomWeapon == 2){
            this.type = "Sword";
        } else if(randomWeapon == 3){
            this.type = "Hammer";
        } else{
            this.type = "";
        }
    }

}

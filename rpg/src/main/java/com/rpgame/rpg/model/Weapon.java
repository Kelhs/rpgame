package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Weapon extends Stuff {
    
    public Weapon(){

    }

    public Weapon(Loot loot, String rarity, String categorie){   
        super(loot, rarity);
        this.categorie = categorie;
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

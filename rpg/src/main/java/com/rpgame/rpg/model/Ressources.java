package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ressources{
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "lootId")
    protected Loot loot;
    private String categorie = "Ressources";
    private String type;
    private int maxPossibilities = 3;
    private double randomStuff = Math.floor((Math.random() * maxPossibilities) + 1);
    
    public Ressources(Loot loot){
        this.loot = loot;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return this.type;
    }

    public void setType(){
        if(randomStuff == 1){
            this.type = "Gold";
        } else if(randomStuff == 2){
            this.type = "Wood";
        } else if(randomStuff == 3){
            this.type = "Stone";
        } else{
            this.type = "Error";
        }
    }


    /**
     * @return string return the categorie
     */
    public String getCategorie() {
        return categorie;
    }
}

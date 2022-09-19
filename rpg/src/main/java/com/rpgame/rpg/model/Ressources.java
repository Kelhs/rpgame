package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Ressources{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ressources_seq_gen")
    @SequenceGenerator(name = "ressources_seq_gen", sequenceName = "RESSOURCES_SEQ")
    private int id;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "lootId")
    protected Loot loot;
    private String categorie = "Ressources";
    private String type;
    
    
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
        int maxPossibilities = 3;
        double randomStuff = Math.floor((Math.random() * maxPossibilities) + 1);
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

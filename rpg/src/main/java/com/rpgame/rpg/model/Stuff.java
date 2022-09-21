package com.rpgame.rpg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
// @Table(name = "stuff", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Inheritance(strategy=InheritanceType.JOINED)
public class Stuff{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "stuff_seq_gen")
    @SequenceGenerator(name = "stuff_seq_gen", sequenceName = "STUFF_SEQ")
    private int id;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "lootId")
    protected Loot loot;
    protected String categorie;
    protected String type;
    private String rarity;
    
    public Stuff(){

    }
    public Stuff(Loot loot, String rarity){
        this.loot = loot;
        this.rarity = rarity;
    }

    public int getId(){
        return id;
    }

    public String getRarity(){
        return rarity;
    }

    /**
     * @return String return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(){
        int maxPossibilities = 2;
        double randomStuff = Math.floor((Math.random() * maxPossibilities) + 1);
        if(randomStuff == 1){
            this.categorie = "Armor";
        } else if(randomStuff == 2){
            this.categorie = "Weapon";
        }        
    }

    /**
     * @return String return the type
     */
    public String getType(){
        return type;
    }

    public void setType(){
        this.type = type;
    }
}

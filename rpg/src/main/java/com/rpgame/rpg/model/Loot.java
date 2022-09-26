package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private int characterLuck = 0;
    
    public Loot(){

    }
    
    public Loot(int characterLuck){
        this.characterLuck = characterLuck;
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return int return the characterLuck
     */
    public int getCharacterLuck() {
        return characterLuck;
    }

    /**
     * @return int return the percentRest
     */
    private double getPercentRest() {
        if(characterLuck > 30){
            return this.characterLuck - 30;
        } else {
            return 0;
        }
    }

    /**
     * @return int return the stuffPercent
     */
    public double getStuffPercent() {
        double stuffPercent = 25;

        //On cap l'impact de la statistique de chance a 55 pour qu'elle n'ai pas d'effet sur ce taux de drop au dela de 55
        if(characterLuck <= 55){
            return stuffPercent + characterLuck;
        } else {
            //Au delà de 55 de stat de chance, le pourcentage n'evolue plus et est cap à 80.
            return 80;            
        }
    }

    /**
     * @return int return the ressourcesPercent
     */
    public double getRessourcesPercent() {
        double ressourcesPercent = 45;

        //On cap l'impact de la statistique de chance a 55 pour qu'elle n'ai pas d'effet sur ce taux de drop au dela de 55
        if(characterLuck <= 55){
            return ressourcesPercent - getPercentRest();
        } 
        else {
            //Au delà de 55 de stat de chance, le pourcentage n'evolue plus et est cap à 20.
            return 20;            
        }
    }

    /**
     * @return int return the nothingPercent
     */
    public double getNothingPercent() {
        double nothingPercent = 0;
        if(characterLuck <= 30){
            return nothingPercent - characterLuck;
        } else {
            return 0;
        }
    }

    /**
     * @return String return the type
     */
    public String getType(){
        return type;
    }

    public void setType() {
        double lootRandom = Math.floor(Math.random() * 100);
        //Si le chiffre random est comprit dans la fourchette de pourcentage d'équipements c'est un équipement
        if (lootRandom <= getStuffPercent()){
            this.type = "Stuff";
        } 
        //Si le chiffre random est comprit dans la fourchette de pourcentage de Ressources c'est une ressource
        else if(lootRandom > getStuffPercent() && lootRandom < getStuffPercent() + getRessourcesPercent()){
            this.type = "Ressource";
        } 
        //Si le chiffre random est supérieur à la fourchette de pourcentage d'équipements + celle de ressources, ce n'est rien
        else {
            this.type = "Rien";
        }    
    }
}

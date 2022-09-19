package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Rarity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rarity_seq_gen")
    @SequenceGenerator(name = "rarity_seq_gen", sequenceName = "RARITY_SEQ")
    private int id;

    // @OneToOne(fetch =  FetchType.LAZY)
    // @JoinColumn(name = "stuffId")
    // protected Stuff stuff;
    private int characterLuck = 0;
    private int legendaryPercent = 20;
    private int epicPercent = 80;
    private int rarePercent = 200;
    private int uncommonPercent = 300;
    private int commonPercent = 400;
    private int commonToUncommonRest = 0;


    public Rarity(int characterLuck){
        this.characterLuck = characterLuck;
    }

    public int getCommonToUncommonRest(){
        return commonToUncommonRest;
    }
    /**
     * @return String return the rarity
     */
    public String getRarity() {
        double rarityRandom = Math.floor(Math.random() * 1000);

        if(rarityRandom <= getLegendaryPercent()){
            return "Legendary";
        } else if(rarityRandom > getLegendaryPercent() && rarityRandom <= getLegendaryPercent() + getEpicPercent()){
            return "Epic";
        } else if(rarityRandom > getLegendaryPercent() + getEpicPercent() && rarityRandom <= getLegendaryPercent() + getEpicPercent() + getRarePercent()){
            return "Rare";
        } else if(rarityRandom > getLegendaryPercent() + getEpicPercent() + getRarePercent() && rarityRandom <= getLegendaryPercent() + getEpicPercent() + getRarePercent() + getUncommonPercent()){
            return "Uncommon";
        } else if(rarityRandom > getLegendaryPercent() + getEpicPercent() + getRarePercent() + getUncommonPercent() && rarityRandom <= getLegendaryPercent() + getEpicPercent() + getRarePercent() + getUncommonPercent() + getCommonPercent()){
            return "Common";
        } else {
            return "oupsi";
        }
    }

    /**
     * @return int return the legendaryPercent
     */
    public int getLegendaryPercent() {
        return legendaryPercent + characterLuck;
    }

    /**
     * @return int return the epicPercent
     */
    public int getEpicPercent() {
        return epicPercent + (characterLuck * 2);
    }

    /**
     * @return int return the rarePercent
     */
    public int getRarePercent() {
        return rarePercent + (characterLuck * 3);
    }

    /**
     * @return int return the uncommonPercent
     */
    public int getUncommonPercent() {
        if(getCommonPercent() == 0){
            return uncommonPercent - commonToUncommonRest;
        } else {
            return uncommonPercent;
        }
    }

    /**
     * @return int return the commonPercent
     */
    public int getCommonPercent() {
        if(commonPercent - (6 * characterLuck) < 0){
            commonToUncommonRest = (6 * characterLuck) - 400;
            return 0;
        } else {
            return commonPercent - (6 * characterLuck);
        }
    }
}

package com.rpgame.rpg.web.dto;

import com.rpgame.rpg.model.Stats;

public class StatsDTO {
    private int id;
    private int agility;
    private int intelligence;
    private int luck;
    private int stamina;
    private int strength;
    private int health;
    private int maxHealth;
    private double speed;

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return int return the agility
     */
    public int getAgility() {
        return agility;
    }

    /**
     * @param agility the agility to set
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * @return int return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @return int return the luck
     */
    public int getLuck() {
        return luck;
    }

    /**
     * @param luck the luck to set
     */
    public void setLuck(int luck) {
        this.luck = luck;
    }

    /**
     * @return int return the stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * @param stamina the stamina to set
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * @return int return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public static Stats UpdateStatsDTOToStats(StatsDTO statsDTO, int id){
        Stats statsToUpdate = new Stats(statsDTO.id, statsDTO.agility, statsDTO.intelligence, statsDTO.luck, statsDTO.stamina, statsDTO.strength, statsDTO.health, statsDTO.maxHealth, statsDTO.speed);
        return statsToUpdate;
    }

    public static Stats StatsDTOToStats(StatsDTO statsDTO){
        Stats stats = new Stats(statsDTO.agility, statsDTO.intelligence, statsDTO.luck, statsDTO.stamina, statsDTO.strength, statsDTO.health, statsDTO.maxHealth, statsDTO.speed);
        return stats;
    }
}

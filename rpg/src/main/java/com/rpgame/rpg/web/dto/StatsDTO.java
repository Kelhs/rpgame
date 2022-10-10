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
    private int defense;
    private int attackSpeed;
    private int exp;
    private int level;

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

    public static Stats UpdateStatsDTOToStats(StatsDTO statsDTO, int id) {
        Stats statsToUpdate = new Stats(statsDTO.id, statsDTO.agility, statsDTO.intelligence, statsDTO.luck,
                statsDTO.stamina, statsDTO.strength, statsDTO.health, statsDTO.maxHealth, statsDTO.speed,
                statsDTO.defense, statsDTO.attackSpeed, statsDTO.exp, statsDTO.level);
        return statsToUpdate;
    }

    public static Stats StatsDTOToStats(StatsDTO statsDTO) {
        Stats stats = new Stats(statsDTO.agility, statsDTO.intelligence, statsDTO.luck, statsDTO.stamina,
                statsDTO.strength, statsDTO.health, statsDTO.maxHealth, statsDTO.speed, statsDTO.defense,
                statsDTO.attackSpeed, statsDTO.exp, statsDTO.level);
        return stats;
    }

    /**
     * @return int return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return int return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return double return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return int return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @return int return the attackSpeed
     */
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * @param attackSpeed the attackSpeed to set
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * @return int return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return int return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

}

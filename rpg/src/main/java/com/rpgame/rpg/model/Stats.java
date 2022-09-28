package com.rpgame.rpg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @SequenceGenerator(name = "stats_seq_gen", sequenceName = "STATS_SEQ")
    private int id;

    private int luck;
    private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private int health;
    private int maxHealth;
    private double speed;

    public Stats(){
    }

    public Stats(int agility, int intelligence, int luck, int stamina, int strength, int health, int maxHealth, double speed){
        this.agility = agility;
        this.intelligence = intelligence;
        this.luck = luck;
        this.stamina = stamina;
        this.strength = strength;
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
    } 

    public Stats(int id, int agility, int intelligence, int luck, int stamina, int strength, int health, int maxHealth, double speed){
        this.id = id;
        this.agility = agility;
        this.intelligence = intelligence;
        this.luck = luck;
        this.stamina = stamina;
        this.strength = strength;
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
    }
    


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
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
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

}

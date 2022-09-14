package com.rpgame.rpg.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rpgame.rpg.model.Armor;
import com.rpgame.rpg.model.Loot;
import com.rpgame.rpg.model.Rarity;
import com.rpgame.rpg.model.Ressources;
import com.rpgame.rpg.model.Stats;
import com.rpgame.rpg.model.Stuff;
import com.rpgame.rpg.model.Weapon;
import com.rpgame.rpg.web.dao.LootDAO;
import com.rpgame.rpg.web.dao.RarityDAO;
import com.rpgame.rpg.web.dao.RessourcesDAO;
import com.rpgame.rpg.web.dao.StatsDAO;
import com.rpgame.rpg.web.dao.StuffDAO;

@RestController
public class LootController {

    @Autowired
    private final LootDAO lootDAO;
    @Autowired
    private final StatsDAO statsDAO;
    @Autowired
    private final StuffDAO stuffDAO;
    @Autowired
    private final RessourcesDAO ressourcesDAO;

    public LootController(LootDAO lootDAO, StatsDAO statsDAO, StuffDAO stuffDAO, RessourcesDAO ressourcesDAO){
        this.lootDAO = lootDAO;
        this.statsDAO = statsDAO;
        this.stuffDAO = stuffDAO;
        this.ressourcesDAO = ressourcesDAO;
    }

    @GetMapping("/Loot")
    public List<Loot> lootList() {
       return lootDAO.findAll();
    }

    @GetMapping("/Stats/{id}")
    public Stats getStatsById(@PathVariable int id){
        return statsDAO.findById(id);
    }

    @PostMapping("/Randomdrop")
    public void createRandomDrop(){
        int luck = getStatsById(1).getLuck();
        Loot loot = new Loot(luck);
        loot.setType();
        String lootType = loot.getType();
        lootDAO.save(loot);
        if(lootType == "Stuff"){
            Rarity rarity = new Rarity(luck);
            String stuffRarity = rarity.getRarity();
            Stuff stuff = new Stuff(loot, stuffRarity);
            String stuffCategorie = stuff.getCategorie();
            if(stuffCategorie == "Armor"){
                stuff = new Armor(loot, stuffRarity);
            } else if (stuffCategorie == "Weapon"){
                stuff = new Weapon(loot, stuffRarity);
            }
            stuff.setType();
            stuffDAO.save(stuff);
        } else if (lootType == "Ressource"){
            Ressources ressource = new Ressources(loot);
            ressource.setType();
            ressourcesDAO.save(ressource);
        } else {

        }        
    }
}

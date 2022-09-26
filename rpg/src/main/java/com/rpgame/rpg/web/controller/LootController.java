package com.rpgame.rpg.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpgame.rpg.model.Loot;
import com.rpgame.rpg.model.Rarity;
import com.rpgame.rpg.model.Ressources;
import com.rpgame.rpg.model.Stuff;
import com.rpgame.rpg.web.dao.LootDAO;
import com.rpgame.rpg.web.dao.RessourcesDAO;
import com.rpgame.rpg.web.dao.StuffDAO;
import com.rpgame.rpg.web.dto.StuffDTO;

@RestController
public class LootController {

    @Autowired
    private final LootDAO lootDAO;
    @Autowired
    private final StuffDAO stuffDAO;
    @Autowired
    private final RessourcesDAO ressourcesDAO;

    public LootController(LootDAO lootDAO, StuffDAO stuffDAO, RessourcesDAO ressourcesDAO){
        this.lootDAO = lootDAO;
        this.stuffDAO = stuffDAO;
        this.ressourcesDAO = ressourcesDAO;
    }

    @GetMapping("/Loot")
    public List<Loot> getLootList() {
       return lootDAO.findAll();
    }

    @GetMapping("Loot/{id}")
    public Loot getLootById(int id){
        return lootDAO.findById(id);
    }

    @PostMapping("/Randomdrop/{luck}")
    public Object createRandomDrop(@PathVariable int luck){
        Loot loot = new Loot(luck);
        loot.setType();
        String lootType = loot.getType();
        if(lootType == "Stuff"){
            lootDAO.save(loot);
            Rarity rarity = new Rarity(luck);
            String stuffRarity = rarity.getRarity();
            Stuff stuff = new Stuff(loot, stuffRarity);
            stuff.setCategorie();
            String stuffCategorie = stuff.getCategorie();
            stuff = StuffDTO.StuffDTO2Stuff(loot, stuffRarity, stuffCategorie);
            stuff.setType();
            stuffDAO.save(stuff);
            return stuff;
        } else if (lootType == "Ressource"){
            lootDAO.save(loot);
            Ressources ressource = new Ressources(loot);
            ressource.setType();
            ressourcesDAO.save(ressource);
            return ressource;
        } else {
            return "Aucun loot";
        }  
          
    }
}

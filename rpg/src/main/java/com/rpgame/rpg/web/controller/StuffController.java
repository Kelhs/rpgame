package com.rpgame.rpg.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rpgame.rpg.model.Stuff;
import com.rpgame.rpg.web.dao.StuffDAO;

@RestController
public class StuffController {

    @Autowired
    private final StuffDAO stuffDAO;

    public StuffController(StuffDAO stuffDAO) {
        this.stuffDAO = stuffDAO;
    }

    @GetMapping("/Stuff")
    public List<Stuff> getStuffList() {
        return stuffDAO.findAll();
    }

    @GetMapping("/Stuff/{lootId}")
    public Stuff getStuffByLootId(@PathVariable int lootId) {
        return stuffDAO.findOneByLootId(lootId);
    }
}

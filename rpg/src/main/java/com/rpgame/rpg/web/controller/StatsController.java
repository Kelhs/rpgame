package com.rpgame.rpg.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rpgame.rpg.model.Stats;
import com.rpgame.rpg.web.dao.StatsDAO;
import com.rpgame.rpg.web.dto.StatsDTO;

@RestController
public class StatsController {
    @Autowired
    private final StatsDAO statsDAO;

    public StatsController(StatsDAO statsDAO){
        this.statsDAO = statsDAO;
    }

    @PostMapping("/Stats")
    public void postStats(@RequestBody StatsDTO statsDTO){
        statsDAO.save(StatsDTO.StatsDTOToStats(statsDTO));
    }

    @PutMapping("/Stats/{playerId}")
    @ResponseBody
    public void updateStats(@RequestBody StatsDTO statsDTO, @PathVariable int playerId){
        statsDTO.setId(playerId);
        Stats statsUpdated = StatsDTO.UpdateStatsDTOToStats(statsDTO, playerId);
        statsDAO.save(statsUpdated);
    }
}

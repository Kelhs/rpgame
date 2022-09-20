package com.rpgame.rpg.web.dao;
import com.rpgame.rpg.model.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsDAO extends JpaRepository<Stats, Integer>{
    Stats findById(int id);
}

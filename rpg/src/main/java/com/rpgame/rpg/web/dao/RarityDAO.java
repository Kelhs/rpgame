package com.rpgame.rpg.web.dao;
import com.rpgame.rpg.model.Rarity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RarityDAO extends JpaRepository<Rarity, Integer> {
    Rarity findById(int id);
}

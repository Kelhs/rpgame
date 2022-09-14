package com.rpgame.rpg.web.dao;
import com.rpgame.rpg.model.Loot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LootDAO extends JpaRepository<Loot, Integer> {
    Loot findById(int id);
}

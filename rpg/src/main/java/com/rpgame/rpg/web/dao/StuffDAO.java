package com.rpgame.rpg.web.dao;

import com.rpgame.rpg.model.Stuff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffDAO extends JpaRepository<Stuff, Integer> {
    Stuff findById(int id);

    @Query(value = "SELECT *, 0 AS clazz_ FROM stuff WHERE stuff.loot_id = :lootId", nativeQuery = true)
    Stuff findOneByLootId(@Param("lootId") int lootId);
}

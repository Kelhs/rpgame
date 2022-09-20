package com.rpgame.rpg.web.dao;
import com.rpgame.rpg.model.Stuff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffDAO extends JpaRepository<Stuff, Integer> {
    Stuff findById(int id);
}

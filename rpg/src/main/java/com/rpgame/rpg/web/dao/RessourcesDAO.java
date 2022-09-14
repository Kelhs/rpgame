package com.rpgame.rpg.web.dao;
import com.rpgame.rpg.model.Ressources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourcesDAO extends JpaRepository<Ressources, Integer> {
    Ressources findById(int id);
}

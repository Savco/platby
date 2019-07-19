package com.miso.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import com.miso.entity.Player;
import com.miso.enums.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PlayerDAOImpl implements PlayerDAOCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Player> findBySurname(String surname) {
        return em.createQuery("SELECT p " +
                "FROM Player p " +
                "WHERE p.surname LIKE :playSurname", Player.class)
                .setParameter("playSurname", surname)
                .getResultList();
    }

    @Override
    public List<Player> getAllPlayers() {
        return em.createQuery("SELECT p " +
                "FROM Player p", Player.class)
                .getResultList();
    }

    @Override
    public List<Player> getPlayersInCategory(Category category) {
        System.out.println(category);
        System.out.println(category.ordinal());
        return em.createQuery("SELECT p " +
                "FROM Player p " +
                "WHERE p.category LIKE :category", Player.class)
                .setParameter("category", category)
                .getResultList();
    }
}

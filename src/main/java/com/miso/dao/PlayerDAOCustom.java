package com.miso.dao;

import com.miso.entity.Player;
import com.miso.enums.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerDAOCustom {

    /**
     * Returns information about a player corresponding to given player surname.
     *
     * @param surname is the player surname.
     */
    public List<Player> findBySurname(String surname);

    /**
     * Returns information about all players
     */
    public List<Player> getAllPlayers();

    /**
     * Returns information about all players in given category
     *
     * @param category is player category
     */
    public List<Player> getPlayersInCategory(Category category);
}

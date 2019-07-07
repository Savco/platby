package com.miso.dao;

import com.miso.entity.Player;
import com.miso.enums.Category;

import java.util.List;
import java.util.Set;

public interface PlayerDAO {

    /**
     * Add new player
     * @param player is new player
     */
    public void create(Player player);

    /**
     * Update player
     * @param player is player to be updated
     */
    public void update(Player player);

    /**
     * delete method is used for delete player according to player id in table players
     *
     * @param player is player to be deleted
     * @throws IllegalArgumentException
     *             when there is null.
     */
    public void delete(Player player) throws IllegalArgumentException;

    /**
     * Returns information about a player corresponding to given player id.
     *
     * @param id is the account id.
     */
    public Player findById(Long id);

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

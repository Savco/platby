package com.miso.service;

import com.miso.dao.PlayerDAO;
import com.miso.entity.Player;
import com.miso.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerDAO playerDao;

    public List<Player> getAllPlayers(){
        return playerDao.getAllPlayers();
    }

    public List<Player> getPlayersByCategory(Category category){
        return playerDao.getPlayersInCategory(category);
    }
}

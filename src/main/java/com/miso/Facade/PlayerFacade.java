package com.miso.Facade;

import com.miso.DTO.PlayerDTO;
import com.miso.enums.Category;
import com.miso.service.BeanMappingService;
import com.miso.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlayerFacade {

    @Autowired
    PlayerService playerService;

    @Autowired
    private BeanMappingService beanMappingService;

    public List<PlayerDTO> getAllPlayers(){

        return beanMappingService.mapTo(playerService.getAllPlayers(), PlayerDTO.class);
    }

    public List<PlayerDTO> getPlayersByCategory(Category cat){

        return beanMappingService.mapTo(playerService.getPlayersByCategory(cat), PlayerDTO.class);
    }
}

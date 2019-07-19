package com.miso.dao;

import com.miso.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerDAO extends CrudRepository<Player, Long>, PlayerDAOCustom {
}

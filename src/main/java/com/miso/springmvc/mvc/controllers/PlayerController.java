package com.miso.springmvc.mvc.controllers;

import com.miso.DTO.PlayerDTO;
import com.miso.Facade.PlayerFacade;
import com.miso.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    private PlayerFacade playerFacade;

    /**
     * Get list of Players curl -i -X GET
     * http://localhost:8080/pa165/rest/players
     *
     * @return list of PlayerDTO
     */
    @RequestMapping(value = "/list/{category}", method = RequestMethod.GET)
    public String listPlayers(Model model, @PathVariable String category) {
        model.addAttribute("players", playerFacade.getPlayersByCategory(Category.valueOf(category)));
        System.out.println(playerFacade.getAllPlayers());
        return "player/list";
    }
}

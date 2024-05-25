package com.graphql.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.graphql.demo.model.Player;
import com.graphql.demo.model.Team;
import com.graphql.demo.service.PlayerService;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findById(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return playerService.create(name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id) {
        return playerService.delete(id);
    }

    @MutationMapping

    public Player update(@Argument Integer id, @Argument String name, @Argument Team team) {
        return playerService.update(id, name, team);
    }
    
}

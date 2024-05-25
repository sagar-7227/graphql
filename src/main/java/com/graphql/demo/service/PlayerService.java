package com.graphql.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import com.graphql.demo.model.Player;
import com.graphql.demo.model.Team;
import jakarta.annotation.PostConstruct;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findById(Integer id) {
        return players.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Player player = players.stream().filter(p -> p.id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Player updatePlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(p -> p.id().equals(id)).findFirst();

        if (optional.isPresent()) {
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, updatePlayer);
        } else {
            throw new IllegalArgumentException("Player not found"); 
        }
        return updatePlayer;
    }

    @PostConstruct
    public void init() {
        players.add(new Player(id.incrementAndGet(), "Messi", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Ronaldo", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Neymar", Team.KKR));
    }

}

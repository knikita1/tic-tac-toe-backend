package com.example.tictactoebackend.storage;

import com.example.tictactoebackend.model.Game;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TempStorageComponent implements TempStorage {

    @Getter
    final Map<String, Game> game;

    public TempStorageComponent(Map<String, Game> game) {
        this.game = new HashMap<>();
    }

    @Override
    public Map<String, Game> getInstance() {
        return game;
    }
}

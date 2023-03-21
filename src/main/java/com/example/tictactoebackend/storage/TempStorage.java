package com.example.tictactoebackend.storage;

import com.example.tictactoebackend.model.Game;

import java.util.Map;

public interface TempStorage {
    Map<String, Game> getInstance();
}

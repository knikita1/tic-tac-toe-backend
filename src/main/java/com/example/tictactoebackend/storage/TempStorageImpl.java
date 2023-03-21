package com.example.tictactoebackend.storage;

import com.example.tictactoebackend.model.Game;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TempStorageImpl {
    @Getter
    private final Map<String, Game> games;

    private TempStorageImpl() {
        games = new HashMap<>();
    }

    private static final class InstanceHolder {
        private static final TempStorageImpl instance = new TempStorageImpl();
    }

    public static TempStorageImpl getInstance() {
        return InstanceHolder.instance;
    }
}
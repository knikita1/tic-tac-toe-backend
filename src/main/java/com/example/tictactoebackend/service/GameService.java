package com.example.tictactoebackend.service;

import com.example.tictactoebackend.exceptions.GameNotFoundException;
import com.example.tictactoebackend.exceptions.InvalidParameterException;
import com.example.tictactoebackend.model.Game;
import com.example.tictactoebackend.model.Turn;

public interface GameService {
    Game createGame();

    Game connectToAGame(String gameID) throws GameNotFoundException;

    Game makeTurn(Turn turn) throws GameNotFoundException, InvalidParameterException;
    void switchTurn(Turn turn) throws GameNotFoundException;
}

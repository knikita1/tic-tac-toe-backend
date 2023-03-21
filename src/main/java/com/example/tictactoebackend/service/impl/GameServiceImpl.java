package com.example.tictactoebackend.service.impl;

import com.example.tictactoebackend.exceptions.GameNotFoundException;
import com.example.tictactoebackend.exceptions.InvalidParameterException;
import com.example.tictactoebackend.model.*;
import com.example.tictactoebackend.service.GameService;
import com.example.tictactoebackend.storage.TempStorage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {

    private final TempStorage tempStorage;

    private final String charsForGenerator = "0123456789abcdefghijklmnopqrstuvwxyz";

    @Autowired
    public GameServiceImpl(TempStorage tempStorageImpl) {
        this.tempStorage = tempStorageImpl;
    }

    @Override
    @Transactional
    public Game createGame() {

        String gameId = RandomStringUtils.random(5, charsForGenerator);
        String player0Id = RandomStringUtils.random(5, charsForGenerator);


        Game game = new Game()
                .setGameId(gameId)
                .setGameBoard(new GameBoard())
                .setGameStatus(GameStatus.NEW)
                .setPlayer0Id(player0Id)
                .setCurrentTurn(XO.X);
        tempStorage.getInstance().put(game.getGameId(), game);
        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Game connectToAGame(String gameID) throws GameNotFoundException {
        Game game = tempStorage.getInstance().get(gameID);

        if (game == null) {
            throw new GameNotFoundException(HttpStatus.BAD_REQUEST, "Game not found");
        }

        String playerId = RandomStringUtils.random(5, charsForGenerator);
        game.setGameStatus(GameStatus.STARTED);
        game.setPlayer1Id(playerId);
        game.setIdOfActivePlayer(playerId);
        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Game makeTurn(Turn turn) throws GameNotFoundException, InvalidParameterException {

        Game game = tempStorage.getInstance().get(turn.getGameId());

        if (game == null) {
            throw new GameNotFoundException(HttpStatus.BAD_REQUEST, "Game not found");
        }

        if (game.getGameStatus()==GameStatus.FINISHED) {
            throw new InvalidParameterException(HttpStatus.BAD_REQUEST, "Game already finished");
        }

        if (!game.getIdOfActivePlayer().equals(turn.getPlayersId())) {
            throw new InvalidParameterException(HttpStatus.BAD_REQUEST, "Invalid parameter");
        }


        game.makeTurn(turn.getX(), turn.getY());
        if (game.isWinner()){
            game.setGameStatus(GameStatus.FINISHED);
        };

        game.thereIsNoE();

        return game;
    }

    @Override
    @Transactional
    public void switchTurn(Turn turn) throws GameNotFoundException {
        Game game = tempStorage.getInstance().get(turn.getGameId());
        if (game == null) {
            throw new GameNotFoundException(HttpStatus.BAD_REQUEST, "Game not found");
        }

        game.changeTurn();
    }
}

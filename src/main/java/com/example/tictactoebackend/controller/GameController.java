package com.example.tictactoebackend.controller;

import com.example.tictactoebackend.dto.CreateConnectRequestDto;
import com.example.tictactoebackend.dto.CreateConnectResponseDto;
import com.example.tictactoebackend.dto.TurnResponseDto;
import com.example.tictactoebackend.exceptions.GameNotFoundException;
import com.example.tictactoebackend.exceptions.InvalidParameterException;
import com.example.tictactoebackend.model.Game;
import com.example.tictactoebackend.model.Turn;
import com.example.tictactoebackend.model.XO;
import com.example.tictactoebackend.service.impl.GameServiceImpl;
import com.example.tictactoebackend.storage.TempStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@CrossOrigin(origins = "*")
public class GameController {

    private final GameServiceImpl gameService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GameController(GameServiceImpl gameService, SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/game/create")
    public ResponseEntity<CreateConnectResponseDto> createNewGame() {
        Game game = gameService.createGame();
        CreateConnectResponseDto createGameResponseDto = new CreateConnectResponseDto()
                .setGameId(game.getGameId())
                .setGameBoard(game.getGameBoard())
                .setWinner(game.getWinner())
                .setPlayersId(game.getPlayer0Id())
                .setPlayersXo(XO.O)
                .setCurrentTurn(game.getCurrentTurn());
        return ResponseEntity.ok(createGameResponseDto);
    }

    @GetMapping("/game/test")
    public ResponseEntity<String> test() {
        Collection<Game> games = TempStorageImpl.getInstance().getGames().values();
        return ResponseEntity.ok("test");
    }

    @PostMapping("/game/connect")
    public ResponseEntity<CreateConnectResponseDto> connectToAGame(@RequestBody CreateConnectRequestDto connectToAGameRequestDto) throws GameNotFoundException {

        Game game = gameService.connectToAGame(connectToAGameRequestDto.getGameId());

        CreateConnectResponseDto connectToAGameResponseDto = new CreateConnectResponseDto()
                .setGameId(game.getGameId())
                .setGameBoard(game.getGameBoard())
                .setPlayersId(game.getPlayer1Id())
                .setPlayersXo(XO.X)
                .setCurrentTurn(game.getCurrentTurn());

        return ResponseEntity.ok(connectToAGameResponseDto);
    }

    @PostMapping("/game/turn")
    public void makeTurn(@RequestBody Turn turn) throws GameNotFoundException, InvalidParameterException {

        Game game = gameService.makeTurn(turn);

        TurnResponseDto turnResponse = new TurnResponseDto();
        turnResponse
                .setX(turn.getX())
                .setY(turn.getY())
                .setCurrentTurn(game.getCurrentTurn())
                .setWinner(game.getWinner())
                .setXo(game.getCurrentTurn());

        gameService.switchTurn(turn);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + turn.getGameId(), turnResponse);
    }
}

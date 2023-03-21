package com.example.tictactoebackend.model;

import com.example.tictactoebackend.exceptions.InvalidParameterException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Accessors(chain = true)
public class Game {
    private String gameId;
    private GameBoard gameBoard;
    private GameStatus gameStatus;
    private XO currentTurn;
    private String player0Id;
    private String player1Id;
    private String idOfActivePlayer;
    private XO winner;

    public boolean isWinner() {

        List<XO> checkList = gameBoard.getCheckList();
        Integer maxForWin = 3;

        if (checkListForConsecutiveElements(checkList, currentTurn, maxForWin)) {
            this.winner = currentTurn;
            return true;
        }
        return false;
    }

    boolean checkListForConsecutiveElements(List<XO> list, XO xo, Integer maxForWin) {
        int counter = 0;
        for (XO value : list) {

            if (value.equals(xo)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter >= maxForWin) {
                return true;
            }
        }
        return false;
    }

    public void thereIsNoE() {

        if (winner == XO.X | winner == XO.X)
            return;

        for (List<XO> list : gameBoard.gameBoardArray
        ) {
            if (isElementInLine(list, XO.E)) {
                return;
            }
        }
        this.winner = XO.E;
    }

    Boolean isElementInLine(List<XO> list, XO xo) {
        for (XO element : list
        ) {
            if (element.equals(xo)) {
                return true;
            }
        }
        return false;
    }

    public void makeTurn(int x, int y) throws InvalidParameterException {

        int boardSize = gameBoard.gameBoardArray.size();

        if ((x < 0 | x >= boardSize) | (y < 0 | y >= boardSize)) {
            throw new InvalidParameterException(HttpStatus.BAD_REQUEST, "Invalid parameters");
        }
        gameBoard.getGameBoardArray().get(y).set(x, currentTurn);
    }

    public void changeTurn() {

        if (this.currentTurn == XO.X) {
            this.currentTurn = XO.O;
        } else {
            this.currentTurn = XO.X;
        }

        if (this.idOfActivePlayer.equals(player0Id)) {
            this.idOfActivePlayer = player1Id;
        } else {
            this.idOfActivePlayer = player0Id;
        }

    }
}

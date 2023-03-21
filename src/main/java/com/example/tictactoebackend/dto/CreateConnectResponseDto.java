package com.example.tictactoebackend.dto;

import com.example.tictactoebackend.model.GameBoard;
import com.example.tictactoebackend.model.XO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateConnectResponseDto {
    private String gameId;
    private GameBoard gameBoard;
    private XO winner;
    private String playersId;
    private XO playersXo;
    private XO currentTurn;

}
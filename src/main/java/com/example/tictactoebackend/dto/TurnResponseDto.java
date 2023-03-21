package com.example.tictactoebackend.dto;

import com.example.tictactoebackend.model.XO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TurnResponseDto {
    private Integer x;
    private Integer y;
    private XO currentTurn;
    private XO xo;
    private XO winner;
}
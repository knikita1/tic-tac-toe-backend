package com.example.tictactoebackend.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Turn {
    private String gameId;
    private String playersId;
    private Integer x;
    private Integer y;
}

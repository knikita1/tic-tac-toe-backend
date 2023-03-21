package com.example.tictactoebackend.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateConnectRequestDto {
    private String gameId;
}

package com.example.tictactoebackend.model;

import lombok.Getter;

@Getter
public enum XO {
    X('x'),
    O('o'),
    E('e');
    final Character value;

    XO(Character value) {
        this.value = value;
    }
}

package com.example.tictactoebackend.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameBoardTest {

    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();

        List<List<XO>> example = new ArrayList<>();
        example.add(new ArrayList<>() {
            {
                add(XO.E);
            }

            {
                add(XO.O);
            }

            {
                add(XO.E);
            }
        });
        example.add(new ArrayList<>() {
            {
                add(XO.E);
            }

            {
                add(XO.O);
            }

            {
                add(XO.E);
            }
        });
        example.add(new ArrayList<>() {
            {
                add(XO.E);
            }

            {
                add(XO.O);
            }

            {
                add(XO.E);
            }
        });

        gameBoard.setGameBoardArray(example);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCheckList() {
        List<XO> expected = new ArrayList<>(
                Arrays.asList(
                        XO.E, XO.O, XO.E, XO.E,
                        XO.E, XO.O, XO.E, XO.E,
                        XO.E, XO.O, XO.E, XO.E,

                        XO.E, XO.E, XO.E, XO.E,
                        XO.O, XO.O, XO.O, XO.E,
                        XO.E, XO.E, XO.E, XO.E,

                        XO.E, XO.O, XO.E, XO.E,
                        XO.E, XO.O, XO.E)
        );


        Assertions.assertEquals(gameBoard.getCheckList(), expected);
    }
}
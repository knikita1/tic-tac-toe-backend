package com.example.tictactoebackend.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
public class GameBoard {
    List<List<XO>> gameBoardArray;

    public GameBoard() {
        gameBoardArray = Stream.generate(() -> Stream.generate(() ->
                        XO.E
                )
                .limit(3)
                .collect(Collectors.toList())).limit(3).collect(Collectors.toList());
    }

    public List<XO> getCheckList() {
        List<XO> checkList = new ArrayList<>();

        int gameBoardLength = gameBoardArray.size();

        for (List<XO> row : gameBoardArray
        ) {
            checkList.addAll(row);
            checkList.add(XO.E);
        }


        IntStream.range(0, gameBoardLength).forEach(i -> {
            for (List<XO> xos : gameBoardArray) {
                checkList.add(xos.get(i));
            }
            checkList.add(XO.E);
        });

        for (int i = 0, j = 0; i < gameBoardLength; i++, j++) {
            checkList.add(gameBoardArray.get(i).get(j));
        }
        checkList.add(XO.E);

        for (int i = 0, j = gameBoardLength - 1; i < gameBoardLength; i++, j--) {
            checkList.add(gameBoardArray.get(i).get(j));
        }

        return checkList;
    }
}

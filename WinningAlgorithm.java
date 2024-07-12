package com.example.strategies;

import com.example.models.Board;
import com.example.models.Move;

import java.util.HashMap;

public class WinningAlgorithm {
    private HashMap<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();
    private HashMap<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    private HashMap<Character, Integer> leftDiagonalMap = new HashMap<>();
    private HashMap<Character, Integer> rightDiagonalMap = new HashMap<>();

    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character character = move.getPlayer().getSymbol().getaChar();

        if (updateAndCheck(rowMaps, row, character, board.getSize())) {
            return true;
        }

        if (updateAndCheck(colMaps, col, character, board.getSize())) {
            return true;
        }

        if (row == col) {
            if (updateAndCheckDiagonal(leftDiagonalMap, character, board.getSize())) {
                return true;
            }
        }

        if (row + col == board.getSize() - 1) {
            if (updateAndCheckDiagonal(rightDiagonalMap, character, board.getSize())) {
                return true;
            }
        }

        return false;
    }

    private boolean updateAndCheck(HashMap<Integer, HashMap<Character, Integer>> map, int index, Character character, int boardSize) {
        map.putIfAbsent(index, new HashMap<>());
        HashMap<Character, Integer> charCountMap = map.get(index);
        charCountMap.put(character, charCountMap.getOrDefault(character, 0) + 1);

        return charCountMap.get(character) == boardSize;
    }

    private boolean updateAndCheckDiagonal(HashMap<Character, Integer> map, Character character, int boardSize) {
        map.put(character, map.getOrDefault(character, 0) + 1);

        return map.get(character) == boardSize;
    }

    public void reset() {
        rowMaps.clear();
        colMaps.clear();
        leftDiagonalMap.clear();
        rightDiagonalMap.clear();
    }

    public void printState() {
        System.out.println("Row Maps: " + rowMaps);
        System.out.println("Column Maps: " + colMaps);
        System.out.println("Left Diagonal Map: " + leftDiagonalMap);
        System.out.println("Right Diagonal Map: " + rightDiagonalMap);
    }
}

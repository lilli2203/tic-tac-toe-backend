package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public Board(int size) {
        this.size = size;
        board = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());

            for (int j = 0; j < size; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void printBoard() {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|    |");
                } else {
                    System.out.print("| " + cell.getPlayer().getSymbol().getaChar() + " |");
                }
            }
            System.out.println();
        }
        printColumnNumbers();
    }

    private void printColumnNumbers() {
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print("  " + i + "   ");
        }
        System.out.println();
    }

    public boolean isFull() {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Cell> getEmptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    emptyCells.add(cell);
                }
            }
        }
        return emptyCells;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board.get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void placeMove(int row, int col, Player player) {
        if (isValidMove(row, col)) {
            board.get(row).get(col).setPlayer(player);
            board.get(row).get(col).setCellState(CellState.FILLED);
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }
}

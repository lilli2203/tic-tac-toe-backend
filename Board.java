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

    public boolean checkWin(Player player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    private boolean checkRows(Player player) {
        for (List<Cell> row : board) {
            if (row.stream().allMatch(cell -> cell.getPlayer() != null && cell.getPlayer().equals(player))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(Player player) {
        for (int col = 0; col < size; col++) {
            boolean win = true;
            for (int row = 0; row < size; row++) {
                if (board.get(row).get(col).getPlayer() == null || !board.get(row).get(col).getPlayer().equals(player)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    private boolean checkDiagonals(Player player) {
        boolean mainDiagonal = true;
        boolean antiDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (board.get(i).get(i).getPlayer() == null || !board.get(i).get(i).getPlayer().equals(player)) {
                mainDiagonal = false;
            }
            if (board.get(i).get(size - i - 1).getPlayer() == null || !board.get(i).get(size - i - 1).getPlayer().equals(player)) {
                antiDiagonal = false;
            }
        }
        return mainDiagonal || antiDiagonal;
    }

    public void resetBoard() {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                cell.setPlayer(null);
                cell.setCellState(CellState.EMPTY);
            }
        }
    }
}

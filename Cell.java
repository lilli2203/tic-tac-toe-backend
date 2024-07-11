package com.example.models;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
        this.player = null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isOccupied() {
        return cellState != CellState.EMPTY;
    }

    public void reset() {
        this.cellState = CellState.EMPTY;
        this.player = null;
    }

    @Override
    public String toString() {
        if (cellState == CellState.EMPTY) {
            return "Cell[" + row + ", " + col + "] is empty.";
        } else {
            return "Cell[" + row + ", " + col + "] is occupied by " + player.getName() + ".";
        }
    }

    public String display() {
        if (cellState == CellState.EMPTY) {
            return " ";
        } else {
            return player.getSymbol().getaChar() + "";
        }
    }
}

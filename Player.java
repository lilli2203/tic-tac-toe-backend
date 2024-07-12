package com.example.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        int row, col;
        while (true) {
            System.out.println(name + ", enter the row number where you want to make a move:");
            row = scanner.nextInt();

            System.out.println(name + ", enter the column number where you want to make a move:");
            col = scanner.nextInt();

            if (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() && board.isValidMove(row, col)) {
                break;
            } else {
                System.out.println("Invalid move. Please enter a valid row and column.");
            }
        }

        return new Move(new Cell(row, col), this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}

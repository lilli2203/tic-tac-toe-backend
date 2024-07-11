package com.example.controllers;

import com.example.exceptions.InvalidMoveException;
import com.example.models.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
    public Game startGame(int dimension, List<Player> players) throws InvalidMoveException {
        validatePlayers(players);
        return new Game(dimension, players);
    }

    private void validatePlayers(List<Player> players) throws InvalidMoveException {
        Set<Character> symbols = new HashSet<>();
        for (Player player : players) {
            if (!symbols.add(player.getSymbol().getSymbol())) {
                throw new InvalidMoveException("Two players cannot have the same symbol: " + player.getSymbol().getSymbol());
            }
        }
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
        logMove(game);
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    private void logMove(Game game) {
        Move lastMove = game.getLastMove();
        if (lastMove != null) {
            System.out.println("Player " + lastMove.getPlayer().getName() + " moved to position (" + lastMove.getPosition().getRow() + ", " + lastMove.getPosition().getCol() + ")");
        }
    }

    public void printMovesHistory(Game game) {
        List<Move> moves = game.getMovesHistory();
        System.out.println("Moves History:");
        for (Move move : moves) {
            System.out.println("Player " + move.getPlayer().getName() + " moved to position (" + move.getPosition().getRow() + ", " + move.getPosition().getCol() + ")");
        }
    }
}

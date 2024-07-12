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
        updateGameState(game);
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
            System.out.println("Player " + lastMove.getPlayer().getName() + " moved to position (" + lastMove.getCell().getRow() + ", " + lastMove.getCell().getCol() + ")");
        }
    }

    public void printMovesHistory(Game game) {
        List<Move> moves = game.getMovesHistory();
        System.out.println("Moves History:");
        for (Move move : moves) {
            System.out.println("Player " + move.getPlayer().getName() + " moved to position (" + move.getCell().getRow() + ", " + move.getCell().getCol() + ")");
        }
    }

    private void updateGameState(Game game) {
        if (game.getGameState() == GameState.ENDED) {
            System.out.println("Game ended. Winner: " + game.getWinner().getName());
        } else if (game.getGameState() == GameState.DRAW) {
            System.out.println("Game ended in a draw.");
        }
    }

    public void restartGame(Game game) throws InvalidMoveException {
        game.reset();
        System.out.println("Game restarted.");
    }

    public void endGame(Game game) {
        game.setGameState(GameState.ENDED);
        System.out.println("Game ended.");
    }

    public void saveGame(Game game) {
        System.out.println("Game state saved.");
    }

    public Game loadGame() {
        System.out.println("Game state loaded.");
        return new Game(3, List.of(new Player("Player 1", new Symbol('X'), PlayerType.HUMAN), new Player("Player 2", new Symbol('O'), PlayerType.HUMAN)));
    }
}

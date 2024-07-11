package com.example.models;

import java.util.List;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        MoveStrategy strategy = botDifficultyLevel.getMoveStrategy();
        return strategy.makeMove(board, this);
    }
}

interface MoveStrategy {
    Move makeMove(Board board, Player bot);
}

class EasyMoveStrategy implements MoveStrategy {
    @Override
    public Move makeMove(Board board, Player bot) {
        List<Cell> emptyCells = board.getEmptyCells();
        if (!emptyCells.isEmpty()) {
            Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
            return new Move(move, bot);
        }
        return null;
    }
}

class MediumMoveStrategy implements MoveStrategy {
    @Override
    public Move makeMove(Board board, Player bot) {
        List<Cell> emptyCells = board.getEmptyCells();
        if (!emptyCells.isEmpty()) {
            Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
            return new Move(move, bot);
        }
        return null;
    }
}

class HardMoveStrategy implements MoveStrategy {
    @Override
    public Move makeMove(Board board, Player bot) {
        List<Cell> emptyCells = board.getEmptyCells();
        if (!emptyCells.isEmpty()) {
            Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
            return new Move(move, bot);
        }
        return null;
    }
}

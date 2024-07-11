package com.example.models;

public enum BotDifficultyLevel {
    EASY("Makes random moves with no strategy"),
    MEDIUM("Attempts to block opponent's winning moves"),
    HARD("Uses a minimax algorithm to make optimal moves");

    private final String description;

    BotDifficultyLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public MoveStrategy getMoveStrategy() {
        switch (this) {
            case EASY:
                return new EasyMoveStrategy();
            case MEDIUM:
                return new MediumMoveStrategy();
            case HARD:
                return new HardMoveStrategy();
            default:
                throw new IllegalArgumentException("Unknown difficulty level: " + this);
        }
    }

    interface MoveStrategy {
        void makeMove(Board board, Player bot);
    }

    class EasyMoveStrategy implements MoveStrategy {
        @Override
        public void makeMove(Board board, Player bot) {
            List<Cell> emptyCells = board.getEmptyCells();
            if (!emptyCells.isEmpty()) {
                Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
                board.placeMove(move.getRow(), move.getCol(), bot);
            }
        }
    }

    class MediumMoveStrategy implements MoveStrategy {
        @Override
        public void makeMove(Board board, Player bot) {
            
            List<Cell> emptyCells = board.getEmptyCells();
            if (!emptyCells.isEmpty()) {
                Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
                board.placeMove(move.getRow(), move.getCol(), bot);
            }
        }
    }

    class HardMoveStrategy implements MoveStrategy {
        @Override
        public void makeMove(Board board, Player bot) {
           
            List<Cell> emptyCells = board.getEmptyCells();
            if (!emptyCells.isEmpty()) {
                Cell move = emptyCells.get((int) (Math.random() * emptyCells.size()));
                board.placeMove(move.getRow(), move.getCol(), bot);
            }
        }
    }
}

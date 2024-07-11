package com.example.models;

public enum GameState {
    IN_PROGRESS("The game is currently in progress."),
    ENDED("The game has ended."),
    DRAW("The game ended in a draw.");

    private final String description;

    GameState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }

    public boolean isEnded() {
        return this == ENDED;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}

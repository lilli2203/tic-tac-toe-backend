package com.example.models;

public enum CellState {
    EMPTY("The cell is empty."),
    FILLED("The cell is filled.");

    private final String description;

    CellState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isFilled() {
        return this == FILLED;
    }

    @Override
    public String toString() {
        return description;
    }
}

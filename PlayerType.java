package com.example.models;

public enum PlayerType {
    HUMAN("Human Player"),
    BOT("Computer Player");

    private String description;

    PlayerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

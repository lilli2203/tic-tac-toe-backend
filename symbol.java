package com.example.models;

public class Symbol {
    private char character;

    public Symbol(char character) {
        if (!isValidSymbol(character)) {
            throw new IllegalArgumentException("Invalid symbol. Symbol must be a non-whitespace printable character.");
        }
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        if (!isValidSymbol(character)) {
            throw new IllegalArgumentException("Invalid symbol. Symbol must be a non-whitespace printable character.");
        }
        this.character = character;
    }

    private boolean isValidSymbol(char character) {
        return Character.isLetterOrDigit(character) || Character.isWhitespace(character);
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}


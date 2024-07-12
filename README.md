# Tic Tac Toe Game in Spring Boot

## Introduction

Welcome to the Tic Tac Toe Game implemented in Java using the Spring Boot framework. This project is a classic implementation of the Tic Tac Toe game where two players take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.

## Features

- Two-player mode (Human vs. Human)
- Player vs. Bot with adjustable difficulty levels
- Game board visualization
- Move validation
- Game state tracking (In progress, Ended, Draw)
- Command line interaction
  
## Requirements
Java 11 or higher
Maven 3.6.0 or higher
Spring Boot 2.5.0 or higher
Getting Started

## Build the Project
bash
Copy code
mvn clean install
Run the Application
bash
Copy code
mvn spring-boot:run
Interact with the Game
The game can be played through the command line. Follow the prompts to make moves and see the game board.

## Classes and Their Responsibilities

## Models

Board: Represents the game board and contains methods for initializing the board, printing the board, checking for a win, and validating moves.
Bot: Represents a bot player with different difficulty levels.
Cell: Represents a cell on the board, including its state (EMPTY or FILLED) and the player who occupies it.
Game: Manages the game state, including the board, players, moves history, and the current game state.
Move: Represents a move made by a player, including the cell and player information.
Player: Represents a human player with methods for making moves.
PlayerType: Enum defining player types (HUMAN, BOT).
Symbol: Represents a player's symbol (X or O).
GameState: Enum defining the state of the game (IN_PROGRESS, ENDED, DRAW).
Controllers
GameController: Manages game flow, validates players, makes moves, checks game state, prints the board, and logs moves.
Strategies
WinningAlgorithm: Contains logic to determine if a player has won the game based on the current state of the board.
Exceptions
InvalidMoveException: Custom exception thrown when an invalid move is made.

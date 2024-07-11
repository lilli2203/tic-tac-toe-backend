public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Welcome to the Tic-Tac-Toe game!");
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = List.of(
                new Player("Harsh", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Scaler", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game = gameController.startGame(dimension, players);
        List<String> moveHistory = new ArrayList<>();

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            String moveInfo = "Player " + gameController.getCurrentPlayer(game).getName() + " is making a move.";
            moveHistory.add(moveInfo);

            gameController.makeMove(game);
        }

        gameController.printBoard(game);

        if (game.getGameState().equals(GameState.DRAW)) {
            System.out.println("Game DRAW");
        } else {
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }

        System.out.println("Move History:");
        for (String move : moveHistory) {
            System.out.println(move);
        }
    }
}

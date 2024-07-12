public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerMoveIndex;
    private WinningAlgorithm winningAlgorithm;

    public Game(int dimension, List<Player> players, WinningAlgorithm winningAlgorithm) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
        this.winningAlgorithm = winningAlgorithm;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard() {
        this.board.printBoard();
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() throws InvalidMoveException {
        if (gameState != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Game is not in progress.");
        }

        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s move.");

        Move move = currentPlayer.makeMove(board);

        if (!validateMove(move)) {
            throw new InvalidMoveException("Invalid move made by " + currentPlayer.getName());
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMove = new Move(cellToChange, currentPlayer);
        moves.add(finalMove);
        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        if (winningAlgorithm.checkWinner(board, finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (board.isFull()) {
            gameState = GameState.DRAW;
        }
    }

    public void resetGame() {
        board.resetBoard();
        moves.clear();
        gameState = GameState.IN_PROGRESS;
        winner = null;
        nextPlayerMoveIndex = 0;
    }

    public boolean isGameEnded() {
        return gameState == GameState.ENDED;
    }

    public boolean isGameInProgress() {
        return gameState == GameState.IN_PROGRESS;
    }

    public boolean isGameDraw() {
        return gameState == GameState.DRAW;
    }

    public void startGame() {
        while (gameState == GameState.IN_PROGRESS) {
            try {
                makeMove();
                printBoard();
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }

        if (gameState == GameState.ENDED) {
            System.out.println("The winner is " + winner.getName() + "!");
        } else if (gameState == GameState.DRAW) {
            System.out.println("The game is a draw!");
        }
    }
}

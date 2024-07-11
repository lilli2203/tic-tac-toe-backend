package com.example.models;

public class Move {
    private Cell cell;
    private Player player;

    public Move(Cell cell, Player player) {
        if (cell == null || player == null) {
            throw new IllegalArgumentException("Cell and player cannot be null");
        }
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("Cell cannot be null");
        }
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.player = player;
    }

    public boolean isValid() {
        return cell != null && player != null && cell.getCellState() == CellState.EMPTY;
    }

    public void execute() {
        if (!isValid()) {
            throw new IllegalStateException("Invalid move");
        }
        cell.setPlayer(player);
        cell.setCellState(CellState.FILLED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!cell.equals(move.cell)) return false;
        return player.equals(move.player);
    }

    @Override
    public int hashCode() {
        int result = cell.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cell=" + cell +
                ", player=" + player +
                '}';
    }

    public void undo() {
        if (cell.getPlayer() == player) {
            cell.setPlayer(null);
            cell.setCellState(CellState.EMPTY);
        } else {
            throw new IllegalStateException("Cannot undo move not made by this player");
        }
    }

    public boolean conflictsWith(Move otherMove) {
        return this.cell.equals(otherMove.cell) && this.player.equals(otherMove.player);
    }

    public boolean isAdjacentTo(Move otherMove) {
        int rowDiff = Math.abs(this.cell.getRow() - otherMove.cell.getRow());
        int colDiff = Math.abs(this.cell.getCol() - otherMove.cell.getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }

    public static Move fromString(String moveString, Board board, List<Player> players) {
        String[] parts = moveString.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid move format");
        }

        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        String playerName = parts[2];

        Cell cell = board.getBoard().get(row).get(col);
        Player player = players.stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        return new Move(cell, player);
    }
}

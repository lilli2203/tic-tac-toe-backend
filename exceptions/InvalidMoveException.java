public class InvalidMoveException extends Exception {
    private Player player;
    private Position position;
    private String reason;

    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(String message, Player player, Position position, String reason) {
        super(message);
        this.player = player;
        this.position = position;
        this.reason = reason;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "InvalidMoveException{" +
                "player=" + player +
                ", position=" + position +
                ", reason='" + reason + '\'' +
                '}';
    }

    public static InvalidMoveException createInvalidMoveException(Player player, Position position, String reason) {
        return new InvalidMoveException("Invalid move by player: " + player.getName(), player, position, reason);
    }

    public void printExceptionDetails() {
        System.out.println("Exception Details:");
        System.out.println("Player: " + player.getName());
        System.out.println("Position: " + position);
        System.out.println("Reason: " + reason);
    }
}

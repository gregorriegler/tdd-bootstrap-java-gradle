public class TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Points;
    private int player2Points;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Points++;
        } else {
            player2Points++;
        }
    }

    public String getScore() {
        if (player1Points == 3 && player2Points == 4) {
            return "Advantage " + player2Name;
        }
        if (player1Points == 0 && player2Points == 4) {
            return "Win for " + player2Name;
        }

        return scoreUsingNames();
    }

    private String scoreUsingNames() {
        final String love = "Love";
        final String fifteen = "Fifteen";
        final String thirty = "Thirty";

        if (player1Points == 1 && player2Points == 0) {
            return format(fifteen, love);
        }
        if (player1Points == 1 && player2Points == 2) {
            return format(fifteen, thirty);
        }
        if (player1Points == 3 && player2Points == 2) {
            return format("Forty", thirty);
        }

        if (player1Points == 0 && player2Points == 0) {
            return format(love, "All");
        }

        return "";
    }

    private String format(String player1Score, String player2Score) {
        return player1Score + "-" + player2Score;
    }
}

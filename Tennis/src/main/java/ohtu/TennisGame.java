package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";

        if (m_score1 == m_score2) {
            score = equalPoints();
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            score = advantageOrWin();
        } else {
            score = getGameStatus();
        }
        
        return score;
    }

    public String equalPoints() {
        String score = getStatus(m_score1);
        if (score.equals("Deuce")) {
            return score;
        }
        return score + "-All";
    }

    public String advantageOrWin() {
        String score;
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    public String getStatus(int playerScore) {
        String score;
        switch (playerScore) {
            case 0:
                score = "Love";
                break;
            case 1:
                score = "Fifteen";
                break;
            case 2:
                score = "Thirty";
                break;
            case 3:
                score = "Forty";
                break;
            default:
                score = "Deuce";
                break;
        }
        return score;
    }
    
    public String getGameStatus() {
        return getStatus(m_score1) + "-" + getStatus(m_score2);
    }
}

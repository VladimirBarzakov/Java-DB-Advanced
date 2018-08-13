package footballBetting;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class BetGameId implements Serializable {


    private int game;
    private int bet;

    private BetGameId(){}

    public int getGame() {
        return game;
    }

    public void setGam(int gameId) {
        this.game = gameId;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int betId) {
        this.bet = betId;
    }
}
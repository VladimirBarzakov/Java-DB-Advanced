package footballBetting;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(BetGameId.class)
@Table(name="bet_games")
public class BetGame implements Serializable {

    @EmbeddedId
    private BetGameId id;

    @ManyToOne(targetEntity = Game.class)
    private Game game;


    @ManyToOne(targetEntity = Bet.class)
    private Bet bet;

    @ManyToOne(targetEntity = ResultPrediction.class)
    private ResultPrediction resultPrediction;

    public BetGame(){}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }

    public BetGameId getId() {
        return id;
    }

    public void setId(BetGameId id) {
        this.id = id;
    }
}



package footballBetting;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(PlayerStatisticsId.class)
@Table(name="player_statistics")
public class PlayerStatistics {

    @EmbeddedId
    private PlayerStatisticsId id;

    @ManyToOne(targetEntity = Game.class)
    private Game game;

    @ManyToOne(targetEntity = Player.class)
    private Player player;

    private int scoredGoals;

    private int playerAssists;

    private int playedMinutesDuringGame;
}

@Embeddable
class PlayerStatisticsId implements Serializable{
    int player;
    int game;

    public PlayerStatisticsId(){}

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
}

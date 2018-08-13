package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="result_prediction")
public class ResultPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private enum prediction{Home_Team_Win, Draw_Game, Away_Team_Win}

    @OneToMany(mappedBy = "resultPrediction",targetEntity = BetGame.class)
    private Set<BetGame> betGames;
}

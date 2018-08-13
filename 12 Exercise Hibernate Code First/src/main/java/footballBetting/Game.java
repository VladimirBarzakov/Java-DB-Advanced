package footballBetting;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(targetEntity = Team.class)
    private Team homeTeam;

    @ManyToOne(targetEntity = Team.class)
    private Team awayTeam;

    @Basic
    @Column(name="home_team_goals")
    private int homeGoals;

    @Basic
    @Column(name="away")
    private int awayGoals;

    @Basic
    @Column(name="date_time_game")
    private Date gameDateTime;

    @Basic
    @Column(name="home_win_bet_rate")
    private double homeWinBetRate;

    @Basic
    @Column(name="away_win_bet_rate")
    private double awayWinBetRate;

    @Basic
    @Column(name="draw_bet_rate")
    private double drawBetRate;

    @ManyToOne(targetEntity = Round.class)
    private Round round;

    @ManyToOne(targetEntity = Competition.class)
    private Competition competition;

    @OneToMany(mappedBy = "game",targetEntity = BetGame.class)
    private Set<BetGame> betGame;

    @OneToMany(mappedBy = "game", targetEntity = PlayerStatistics.class)
    private Set<PlayerStatistics> playerStatistics;

    public Game(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getGameDateTime() {
        return gameDateTime;
    }

    public void setGameDateTime(Date gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public double getHomeWinBetRate() {
        return homeWinBetRate;
    }

    public void setHomeWinBetRate(double homeWinBetRate) {
        this.homeWinBetRate = homeWinBetRate;
    }

    public double getAwayWinBetRate() {
        return awayWinBetRate;
    }

    public void setAwayWinBetRate(double awayWinBetRate) {
        this.awayWinBetRate = awayWinBetRate;
    }

    public double getDrawBetRate() {
        return drawBetRate;
    }

    public void setDrawBetRate(double drawBetRate) {
        this.drawBetRate = drawBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Set<BetGame> getBetGame() {
        return betGame;
    }

    public void setBetGame(Set<BetGame> betGame) {
        this.betGame = betGame;
    }

    public Set<PlayerStatistics> getPlayerStatistics() {
        return playerStatistics;
    }

    public void setPlayerStatistics(Set<PlayerStatistics> playerStatistics) {
        this.playerStatistics = playerStatistics;
    }
}

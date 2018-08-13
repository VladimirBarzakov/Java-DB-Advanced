package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="squad_number")
    private int squadNumber;

    @ManyToOne(targetEntity = Team.class)
    private Team team;

    @ManyToOne(targetEntity = Position.class)
    private Position position;

    @OneToMany(mappedBy = "player",targetEntity = PlayerStatistics.class)
    private Set<PlayerStatistics> playerStatistics;

    @Basic
    @Column(name="is_currently_injured")
    private boolean isCurrentlyInjured;

    public Player(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }

    public Set<PlayerStatistics> getPlayerStatistics() {
        return playerStatistics;
    }

    public void setPlayerStatistics(Set<PlayerStatistics> playerStatistics) {
        this.playerStatistics = playerStatistics;
    }
}

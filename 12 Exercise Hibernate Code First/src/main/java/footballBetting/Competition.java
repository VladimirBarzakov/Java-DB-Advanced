package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @ManyToOne(targetEntity = CompetitionType.class)
    private CompetitionType competitionType;

    @OneToMany(mappedBy = "competition", targetEntity = Game.class)
    private Set<Game> games;

    public Competition(){}

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

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}

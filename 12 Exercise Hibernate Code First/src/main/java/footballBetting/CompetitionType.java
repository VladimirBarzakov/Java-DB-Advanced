package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="competition_type")
public class CompetitionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "competitionType",targetEntity = Competition.class)
    private Set<Competition> competitions;

    public CompetitionType(){}

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

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}

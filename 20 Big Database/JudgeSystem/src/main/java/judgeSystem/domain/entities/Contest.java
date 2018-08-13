package judgeSystem.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contest")
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z].{3,}$")
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "contest")
    private List<Problem> problems;

    @ManyToMany
    @JoinTable(
            name = "users_participations",
            joinColumns = {@JoinColumn(name = "contest_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> contestants;

    @OneToMany(mappedBy = "contest")
    private List<MaxResultForContest> maxResultForContests;

    @ManyToMany
    @JoinTable(
            name = "contests_strategies",
            joinColumns = {@JoinColumn(name = "contest_id")},
            inverseJoinColumns = {@JoinColumn(name = "strategy_id")}
    )
    private Set<Strategy> strategies;

    public Contest() {
        this.contestants=new HashSet<>();
        this.problems=new ArrayList<>();
        this.maxResultForContests=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    public List<MaxResultForContest> getMaxResultForContests() {
        return maxResultForContests;
    }

    public void setMaxResultForContests(List<MaxResultForContest> maxResultForContests) {
        this.maxResultForContests = maxResultForContests;
    }

    public Set<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<Strategy> strategies) {
        this.strategies = strategies;
    }
}

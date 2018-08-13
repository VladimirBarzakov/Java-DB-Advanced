package judgeSystem.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp ="^[A-Z].{2,}$")
    private String name;

    @OneToMany(mappedBy = "problem")
    private Set<Submission> submissions;

    @ManyToMany
    @JoinTable(
            name = "problems_users",
            joinColumns = {@JoinColumn(name = "problem_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> contestants;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Contest contest;

    @OneToMany(mappedBy = "problem")
    private Set<MaxResultForProblem> maxResultForProblems;

    @OneToMany
    private Set<Test> tests;

    public Problem() {
        this.contestants=new HashSet<>();
        this.submissions=new HashSet<>();
        this.maxResultForProblems=new HashSet<>();
        this.tests=new HashSet<>();
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

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Set<MaxResultForProblem> getMaxResultForProblems() {
        return maxResultForProblems;
    }

    public void setMaxResultForProblems(Set<MaxResultForProblem> maxResultForProblems) {
        this.maxResultForProblems = maxResultForProblems;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}

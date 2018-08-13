package judgeSystem.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Submission> submissions;

    @OneToMany(mappedBy = "user")
    private Set<MaxResultForContest> maxResultForContests;

    @OneToMany(mappedBy = "user")
    private Set<MaxResultForProblem> maxResultForProblems;

    @ManyToMany(mappedBy = "contestants")
    private Set<Contest> contests;

    @ManyToMany(mappedBy = "contestants")
    private Set<Problem> problems;

    public User() {
        this.contests=new HashSet<>();
        this.submissions=new HashSet<>();
        this.maxResultForContests=new HashSet<>();
        this.maxResultForProblems=new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public Set<MaxResultForContest> getMaxResultForContests() {
        return maxResultForContests;
    }

    public void setMaxResultForContests(Set<MaxResultForContest> maxResultForContests) {
        this.maxResultForContests = maxResultForContests;
    }

    public Set<MaxResultForProblem> getMaxResultForProblems() {
        return maxResultForProblems;
    }

    public void setMaxResultForProblems(Set<MaxResultForProblem> maxResultForProblems) {
        this.maxResultForProblems = maxResultForProblems;
    }

    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}

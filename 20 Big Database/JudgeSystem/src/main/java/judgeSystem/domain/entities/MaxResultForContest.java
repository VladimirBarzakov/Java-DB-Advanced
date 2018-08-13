package judgeSystem.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "max_result_for_contest")
public class MaxResultForContest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double averagePerformance;

    private double overallPoints;

    @ManyToOne
    private Contest contest;

    @ManyToOne
    private User user;

    public MaxResultForContest() {
    }

    public MaxResultForContest(Contest contest, User user) {
        this.contest=contest;
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    public double getOverallPoints() {
        return overallPoints;
    }

    public void setOverallPoints(double overallPoints) {
        this.overallPoints = overallPoints;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

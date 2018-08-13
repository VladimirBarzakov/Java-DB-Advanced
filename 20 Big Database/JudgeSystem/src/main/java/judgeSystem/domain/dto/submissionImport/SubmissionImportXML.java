package judgeSystem.domain.dto.submissionImport;

import judgeSystem.domain.dto.enrollmentInport.UserXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "submission")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionImportXML {

    @XmlElement
    private Double performance;

    @XmlElement
    private UserXML user;

    @XmlElement(name = "problem")
    private ProblemXML problem;

    @XmlElement
    private Double points;

    @XmlElement
    private StrategyXML strategy;

    public SubmissionImportXML() {
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public UserXML getUser() {
        return user;
    }

    public void setUser(UserXML user) {
        this.user = user;
    }

    public ProblemXML getProblem() {
        return problem;
    }

    public void setProblem(ProblemXML problem) {
        this.problem = problem;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public StrategyXML getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyXML strategy) {
        this.strategy = strategy;
    }
}

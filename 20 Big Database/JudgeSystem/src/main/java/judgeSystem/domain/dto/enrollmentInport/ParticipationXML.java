package judgeSystem.domain.dto.enrollmentInport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipationXML {

    @XmlElement(name = "contest")
    private ContestXML contest;

    @XmlElement(name = "user")
    private UserXML user;

    public ParticipationXML() {
    }

    public ContestXML getContest() {
        return contest;
    }

    public void setContest(ContestXML contest) {
        this.contest = contest;
    }

    public UserXML getUser() {
        return user;
    }

    public void setUser(UserXML user) {
        this.user = user;
    }
}

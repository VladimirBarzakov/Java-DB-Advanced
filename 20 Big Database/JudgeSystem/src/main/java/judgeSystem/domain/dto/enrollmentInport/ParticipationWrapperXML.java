package judgeSystem.domain.dto.enrollmentInport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "participations")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipationWrapperXML {

    @XmlElement(name="participation")
    private List<ParticipationXML> participations;

    public ParticipationWrapperXML() {
        this.participations=new ArrayList<>();
    }

    public List<ParticipationXML> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationXML> participations) {
        this.participations = participations;
    }
}

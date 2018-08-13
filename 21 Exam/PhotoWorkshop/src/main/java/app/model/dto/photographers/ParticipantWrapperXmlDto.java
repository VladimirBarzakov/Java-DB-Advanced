package app.model.dto.photographers;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipantWrapperXmlDto {

    @XmlElement(name = "participant")
    private List<PhotographerXmlDto> participants;

    public List<PhotographerXmlDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PhotographerXmlDto> participants) {
        this.participants = participants;
    }

    @XmlAttribute(name = "count")
    public int getCount () {
        return participants.size();
    }
}

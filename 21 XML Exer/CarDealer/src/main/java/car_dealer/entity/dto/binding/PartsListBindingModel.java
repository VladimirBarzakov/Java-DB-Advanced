package car_dealer.entity.dto.binding;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsListBindingModel implements Serializable{

    @XmlElement(name = "part")
    private List<PartsBindingModel> parts;

    public PartsListBindingModel() {
    }

    public List<PartsBindingModel> getParts() {
        return parts;
    }

    public void setParts(List<PartsBindingModel> parts) {
        this.parts = parts;
    }
}

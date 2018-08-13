package car_dealer.entity.dto.view.carAndPartsView;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsViewModel implements Serializable{

    @XmlAttribute(name = "make")
    private String Make;

    @XmlAttribute(name = "model")
    private String Model;

    @XmlAttribute(name = "travelled-distance")
    private long TravelledDistance;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartViewModel> parts;

    public CarAndPartsViewModel() {
    }

    public CarAndPartsViewModel(String make,
                                String model,
                                long travelledDistance,
                                List<PartViewModel> parts) {
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
        this.parts = parts;
    }


    public List<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(List<PartViewModel> parts) {
        this.parts = parts;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}

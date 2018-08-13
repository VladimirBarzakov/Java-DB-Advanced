package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSaleViewModel implements Serializable {

    @XmlAttribute(name = "make")
    private String Make;

    @XmlAttribute(name = "model")
    private String Model;

    @XmlAttribute(name = "travelled-distance")
    private long TravelledDistance;

    public CarSaleViewModel() {
    }

    public CarSaleViewModel(String make, String model, long travelledDistance) {
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
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

package car_dealer.entity.dto.view;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewModel implements Serializable{

    @XmlAttribute(name = "id")
    private int Id;

    @XmlAttribute(name = "make")
    private String Make;

    @XmlAttribute(name = "model")
    private String Model;

    @XmlAttribute(name = "travelled-distance")
    private long TravelledDistance;

    public CarViewModel() {
    }

    public CarViewModel(int id, String make, String model, long travelledDistance) {
        Id = id;
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

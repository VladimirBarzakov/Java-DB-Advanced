package car_dealer.entity.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsListBindingModel implements Serializable{

    @XmlElement(name = "car")
    private List<CarBindingModel> cars;

    public CarsListBindingModel() {
    }

    public List<CarBindingModel> getCars() {
        return cars;
    }

    public void setCars(List<CarBindingModel> cars) {
        this.cars = cars;
    }
}

package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarListViewModel implements Serializable{

    @XmlElement(name = "car")
    private List<CarViewModel> cars;

    public CarListViewModel() {
    }

    public List<CarViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarViewModel> cars) {
        this.cars = cars;
    }
}

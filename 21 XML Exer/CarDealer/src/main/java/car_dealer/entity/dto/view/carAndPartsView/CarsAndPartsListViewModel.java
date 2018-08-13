package car_dealer.entity.dto.view.carAndPartsView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsAndPartsListViewModel implements Serializable{

    @XmlElement(name = "car")
    private List<CarAndPartsViewModel> carsAndParts;

    public CarsAndPartsListViewModel() {
    }

    public List<CarAndPartsViewModel> getCarsAndParts() {
        return carsAndParts;
    }

    public void setCarsAndParts(List<CarAndPartsViewModel> carsAndParts) {
        this.carsAndParts = carsAndParts;
    }
}

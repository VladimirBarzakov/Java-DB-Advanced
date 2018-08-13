package car_dealer.entity.dto.view.carAndPartsView;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartViewModel implements Serializable{

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    public PartViewModel() {
    }

    public PartViewModel(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

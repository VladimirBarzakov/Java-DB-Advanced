package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleListViewModel implements Serializable{

    @XmlElement(name = "sale")
    private List<SalesViewModel> sales;

    public SaleListViewModel() {
    }

    public List<SalesViewModel> getSales() {
        return sales;
    }

    public void setSales(List<SalesViewModel> sales) {
        this.sales = sales;
    }
}

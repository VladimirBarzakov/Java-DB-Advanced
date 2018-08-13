package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierListViewModel implements Serializable{

    @XmlElement(name = "supplier")
    private List<SupplierViewModel> suppliers;

    public SupplierListViewModel() {
    }

    public List<SupplierViewModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierViewModel> supplier) {
        this.suppliers = supplier;
    }
}

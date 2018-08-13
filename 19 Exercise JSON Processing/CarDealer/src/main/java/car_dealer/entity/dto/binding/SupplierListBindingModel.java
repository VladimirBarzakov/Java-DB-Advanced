package car_dealer.entity.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierListBindingModel implements Serializable{

    @XmlElement(name = "supplier")
    private List<SupplierBindingModel> suppliers;


    public SupplierListBindingModel() {
    }

    public List<SupplierBindingModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierBindingModel> suppliers) {
        this.suppliers = suppliers;
    }
}

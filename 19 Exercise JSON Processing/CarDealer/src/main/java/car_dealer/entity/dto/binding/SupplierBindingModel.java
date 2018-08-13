package car_dealer.entity.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierBindingModel implements Serializable{

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierBindingModel() {
    }

    public SupplierBindingModel(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}

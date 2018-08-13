package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemsWrapperDTO {

    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> orderItems;

    public OrderItemsWrapperDTO() {
        this.orderItems=new ArrayList<>();
    }

    public List<OrderItemXMLImportDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemXMLImportDTO> orderItems) {
        this.orderItems = orderItems;
    }
}

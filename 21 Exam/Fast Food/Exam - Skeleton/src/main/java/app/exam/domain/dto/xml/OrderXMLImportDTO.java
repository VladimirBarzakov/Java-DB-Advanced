package app.exam.domain.dto.xml;

import app.exam.domain.entities.OrderType;
import app.exam.parser.DateAdapter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXMLImportDTO{

        @XmlElement
        @NotNull
        private String customer;

        @XmlElement
        @NotNull
        private String employee;

        @XmlElement
        @NotNull
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date date;

        @XmlElement
        @NotNull
        private OrderType type;

        @XmlElement(name = "items")
        private OrderItemsWrapperDTO orderItems;

        public OrderXMLImportDTO() {
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public OrderType getType() {
            return type;
        }

        public void setType(OrderType type) {
            this.type = type;
        }

        public OrderItemsWrapperDTO getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(OrderItemsWrapperDTO orderItems) {
            this.orderItems = orderItems;
        }

}

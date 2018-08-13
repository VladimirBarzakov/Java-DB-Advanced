package app.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String Customer;

    @NotNull
    private Date date;

    @NotNull
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee employee;

    @Transient
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;

    public Order() {
        this.items=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        Set<OrderItem> items = this.getItems();
        for (OrderItem item : items) {
            totalPrice=totalPrice.add(item.getItem().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return totalPrice;
    }
}

package application.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal balance;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Account(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Balance cannot be negative number!");
        }
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="user_name")
    private String userName;

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @Column(name="email",unique = true)
    private String email;

    @Basic
    @Column(name="fullname")
    private String fullname;

    @Basic
    @Column(name="balance")
    private Double balance;

    @OneToMany(mappedBy = "user", targetEntity = Bet.class)
    private Set<Bet> bets;

    private User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }
}

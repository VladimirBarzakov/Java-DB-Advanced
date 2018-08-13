package footballBetting;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    private double betMoney;

    @Basic
    private Date betDateTime;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany(mappedBy = "bet",targetEntity = BetGame.class)
    private Set<BetGame> betGame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(double betMoney) {
        this.betMoney = betMoney;
    }

    public Date getBetDateTime() {
        return betDateTime;
    }

    public void setBetDateTime(Date betDateTime) {
        this.betDateTime = betDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<BetGame> getBetGame() {
        return betGame;
    }

    public void setBetGame(Set<BetGame> betGame) {
        this.betGame = betGame;
    }
}

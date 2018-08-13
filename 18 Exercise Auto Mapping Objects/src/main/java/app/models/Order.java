package app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade =CascadeType.DETACH,fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "order_games",
            joinColumns = {@JoinColumn(name="order_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id",referencedColumnName = "id")}
    )
    private List<Game> games;

    public Order() {
        this.games=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}

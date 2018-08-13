package footballBetting;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="logo")
    private Blob logo;

    @Basic
    @Column(name="initials", length = 3)
    private String initials;


    @ManyToOne(targetEntity = Color.class)
    private Color primaryKitColor;

    @ManyToOne(targetEntity = Color.class)
    private Color secondaryKitColor;


    @ManyToOne(targetEntity = Town.class)
    private Town town;

    @OneToMany(mappedBy = "team",targetEntity = Player.class)
    private Set<Player> players;

    @Basic
    @Column(name="budget")
    private double budget;

    public Team(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }


}

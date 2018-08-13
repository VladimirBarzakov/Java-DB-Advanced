package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "primaryKitColor", targetEntity = Team.class)
    private Set<Team> primaryColors;

    @OneToMany(mappedBy = "secondaryKitColor", targetEntity = Team.class)
    private Set<Team> secondaryColors;

    public Color(){}

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

    public Set<Team> getPrimaryColors() {
        return primaryColors;
    }

    public void setPrimaryColors(Set<Team> primaryColors) {
        this.primaryColors = primaryColors;
    }

    public Set<Team> getSecondaryColors() {
        return secondaryColors;
    }

    public void setSecondaryColors(Set<Team> secondaryColors) {
        this.secondaryColors = secondaryColors;
    }
}

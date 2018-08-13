package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="position")
public class Position {

    @Id
    @Column(name="name", length = 2)
    private String id;

    @Basic
    @Column(name="description")
    private String dscription;

    @OneToMany(mappedBy = "position", targetEntity = Player.class)
    private Set<Player> player;

    public Position(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDscription() {
        return dscription;
    }

    public void setDscription(String dscription) {
        this.dscription = dscription;
    }

    public Set<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Set<Player> player) {
        this.player = player;
    }
}

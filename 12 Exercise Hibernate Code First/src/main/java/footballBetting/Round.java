package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "round",targetEntity = Game.class)
    private Set<Game> games;
}

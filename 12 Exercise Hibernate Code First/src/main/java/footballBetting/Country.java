package footballBetting;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="initials", length = 3)
    private String initials;

    @Basic
    @Column(name="name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="country_continent", joinColumns = @JoinColumn(name="country_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="continent_id",referencedColumnName = "id"))
    @Column(name="continent")
    private Set<Continent> continent;

    @OneToMany(mappedBy = "country",targetEntity = Town.class)
    private Set<Town> towns;

    public Country(){}


    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinent() {
        return continent;
    }

    public void setContinent(Set<Continent> continent) {
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}

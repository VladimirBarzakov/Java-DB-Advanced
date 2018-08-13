package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "procedures")
    private List<AnimalAid> animalAids;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;


    @ManyToOne
    private Vet vet;

    private Date date;

    public Procedure() {
        this.animalAids = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AnimalAid> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<AnimalAid> animalAids) {
        this.animalAids = animalAids;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BigDecimal getCost() {
        BigDecimal cost = BigDecimal.ZERO;
        this.getAnimalAids().stream().map(x->{
            cost.add(x.getPrice());
            return null;
        }).collect(Collectors.toList());
        return cost;
    }


    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

package hospitalDatabase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Table(name="diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="comments")
    private String comments;

    @Column(name="patients")
    @ManyToMany(mappedBy = "diagnoses",targetEntity = Patient.class)
    private Set<Patient> patients;

    public Diagnose(){
        this.setPatients(new HashSet<>());
    }

    public Diagnose(String name, String comments) {
        this();
        this.name = name;
        this.comments = comments;
    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}

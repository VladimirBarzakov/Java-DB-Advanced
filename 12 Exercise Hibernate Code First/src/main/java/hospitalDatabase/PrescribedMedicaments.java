package hospitalDatabase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Table(name="prescribed_medicaments")
public class PrescribedMedicaments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="name", length = 100)
    private String name;

    @ManyToMany(mappedBy = "medicament", targetEntity = Patient.class)
    private Set<Patient> patients;

    public PrescribedMedicaments(){this.setPatients(new HashSet<>());}

    public PrescribedMedicaments(String name) {
        this();
        this.name = name;
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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}

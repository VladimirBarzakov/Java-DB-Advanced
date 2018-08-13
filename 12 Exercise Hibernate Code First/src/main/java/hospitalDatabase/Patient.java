package hospitalDatabase;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="fist_name",nullable = false,length = 100)
    private String firstName;

    @Basic
    @Column(name="last_name",nullable = false, length = 100)
    private String lastName;

    @Basic
    @Column(name="address", length = 100)
    private String address;

    @Basic
    @Column(name="email",length = 100)
    private String email;

    @Basic
    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="photo")
    private Blob photo;

    @Column(name="is_insured")
    private boolean isInsured;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="patients_diagnoses", joinColumns = @JoinColumn(name="patient_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="diagnose_id",referencedColumnName = "id"))
    @Column(name="diagnoses")
    private Set<Diagnose> diagnoses;

    @Column(name="visitation")
    @OneToMany(targetEntity = Visitation.class)
    private Set<Visitation> visitations;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="patients_medicaments", joinColumns = @JoinColumn(name="patient_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="medicament_id",referencedColumnName = "id"))
    @Column(name="medicament")
    private Set<PrescribedMedicaments> medicament;

    public Patient(){
        this.setMedicament(new HashSet<>());
        this.setVisitations(new HashSet<>());
        this.setDiagnoses(new HashSet<>());
    }


    public Patient(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<PrescribedMedicaments> getMedicament() {
        return medicament;
    }

    public void setMedicament(Set<PrescribedMedicaments> medicament) {
        this.medicament = medicament;
    }
}

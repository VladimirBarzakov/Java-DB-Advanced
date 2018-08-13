package hospitalDatabase;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Table(name="visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    @Column(name="visitation_date")
    private Date date;

    @Basic
    @Column(name="comments",length = 2000)
    private String comments;

    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;

    public Visitation(){}

    public Visitation(Date date, String comments, Patient patient) {
        this.date = date;
        this.comments = comments;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

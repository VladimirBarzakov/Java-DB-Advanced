package universitySystem;

import javax.persistence.*;
import java.util.Set;

//@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name="id")
public class Teacher extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    private String email;

    @Basic
    private double salaryPerHour;

    @OneToMany(mappedBy = "lecturer")
    private Set<Course> lecturedCourses;

    private Teacher(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }


    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Set<Course> getLecturedCourses() {
        return lecturedCourses;
    }

    public void setLecturedCourses(Set<Course> lecturedCourses) {
        this.lecturedCourses = lecturedCourses;
    }
}

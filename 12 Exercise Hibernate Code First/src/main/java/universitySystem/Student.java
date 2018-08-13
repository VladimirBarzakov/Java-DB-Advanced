package universitySystem;

import javax.persistence.*;
import java.util.Set;

//@Entity
@Table(name="students")
@PrimaryKeyJoinColumn(name="id")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    private double averageGrades;

    @Basic
    private int attendance;

    public Student(){}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "students", targetEntity = Course.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course>  courses;

    public double getAverageGrades() {
        return averageGrades;
    }

    public void setAverageGrades(double averageGrades) {
        this.averageGrades = averageGrades;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}

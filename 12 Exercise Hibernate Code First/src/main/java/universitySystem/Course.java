package universitySystem;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

//@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    private String name;

    @Basic
    private String description;

    @Basic
    private Date startDate;

    @Basic
    private Date endDate;

    @Basic
    private int credits;

    @OneToOne(targetEntity = Teacher.class)
    private Teacher lecturer;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="student_courses",joinColumns = @JoinColumn(name="course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="student_id",referencedColumnName = "id"))
    @Column(name="students")
    private Set<Student> students;

    public Course(){}


}

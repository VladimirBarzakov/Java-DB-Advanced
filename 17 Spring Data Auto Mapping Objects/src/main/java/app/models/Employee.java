package app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    private Date birthDate;

    private String adress;

    private boolean isOnHoliday;
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee manager;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager",cascade = CascadeType.ALL)
    private Set<Employee> minions;

    public Employee() {
        this.minions=new HashSet<>();
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getMinions() {
        return minions;
    }

    public void setMinions(Set<Employee> minions) {
        this.minions = minions;
    }
}

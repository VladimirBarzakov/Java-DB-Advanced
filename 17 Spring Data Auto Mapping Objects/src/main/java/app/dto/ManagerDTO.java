package app.dto;

import app.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class ManagerDTO {

    private String firstName;

    private String lastName;

    private List<EmployeeDTO> employees;

    public ManagerDTO() {
        this.employees =new ArrayList<>();
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

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> minions) {
        this.employees = minions;
    }
}

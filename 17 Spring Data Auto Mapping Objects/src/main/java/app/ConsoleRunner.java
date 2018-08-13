package app;

import app.dto.EmployeeDTO;
import app.dto.ManagerDTO;
import app.models.Employee;
import app.repositories.EmployeeRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeRepository employeeRepository;

    @Autowired
    public ConsoleRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Employee employeeA = new Employee();
        employeeA.setFirstName("Vladimir");
        employeeA.setLastName("Barzakov");
        employeeA.setAdress("Sofia");
        employeeA.setSalary(new BigDecimal("1000"));

        Employee employeeB = new Employee();
        employeeB.setFirstName("Vania");
        employeeB.setLastName("Slavianova");
        employeeB.setAdress("Sofia");
        employeeB.setSalary(new BigDecimal("900"));

        Employee employeeC = new Employee();
        employeeC.setFirstName("Pepa");
        employeeC.setLastName("Dimitrova");
        employeeC.setAdress("Sofia");
        employeeC.setSalary(new BigDecimal("500"));

        Employee employeeD = new Employee();
        employeeD.setFirstName("Plamen");
        employeeD.setLastName("Stoyanov");
        employeeD.setAdress("Sofia");
        employeeD.setSalary(new BigDecimal("600"));

        Employee employeeE = new Employee();
        employeeE.setFirstName("Elista");
        employeeE.setLastName("Ilieva");
        employeeE.setAdress("Sofia");
        employeeE.setSalary(new BigDecimal("1000"));

        employeeA.setManager(employeeE);
        employeeB.setManager(employeeE);
        employeeC.setManager(employeeE);
        employeeD.setManager(employeeE);
        Set<Employee> employees = new HashSet<>();
        employees.add(employeeA);
        employees.add(employeeB);
        employees.add(employeeC);
        employees.add(employeeD);
        employeeE.setMinions(employees);

       this.employeeRepository.save(employeeE);
       //this.employeeRepository.save(employeeA);
       //this.employeeRepository.save(employeeB);
       //this.employeeRepository.save(employeeD);
       //this.employeeRepository.save(employeeC);

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Employee,ManagerDTO> typeMap =modelMapper.createTypeMap(Employee.class,ManagerDTO.class);
        typeMap.addMapping(Employee::getMinions,ManagerDTO::setEmployees);
        Converter<Set<Employee>, List<EmployeeDTO>> employeeDTOConverter = ctx->ctx.getSource()==null?null:new ArrayList<EmployeeDTO>(ctx.getSource().stream().map(x->modelMapper.map(x,EmployeeDTO.class)).collect(Collectors.toCollection(HashSet::new)));
        typeMap.addMappings(mapper->mapper.using(employeeDTOConverter).map(Employee::getMinions,ManagerDTO::setEmployees));

        ManagerDTO managerDTO = modelMapper.map(employeeE, ManagerDTO.class);
        //ArrayList<Employee> employeeDTOS = employeeE.getMinions().stream().map(x->modelMapper.map(x,EmployeeDTO.class)).collect(Collectors.toList());
        //ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>(employeeE.getMinions().stream().map(x->modelMapper.map(x,EmployeeDTO.class)).collect(Collectors.toCollection(HashSet::new)));
        //managerDTO.setEmployees(employeeDTOS);
        System.out.println(String.format("%s %s | Employees: %d",managerDTO.getFirstName(),managerDTO.getLastName(),managerDTO.getEmployees().size()));
        for (EmployeeDTO employeeDTO : managerDTO.getEmployees()) {
            System.out.println(String.format("  - %s %s %.2f",employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getSalary()));
        }


    }
}

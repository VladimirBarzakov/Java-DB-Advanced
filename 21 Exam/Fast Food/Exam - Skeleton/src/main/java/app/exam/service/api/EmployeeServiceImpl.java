package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.ModelParserImpl;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelParserImpl modelParser;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {
        Employee employee = this.modelParser.convert(importDTO, Employee.class);

        Position position =this.positionRepository.findOneByName(importDTO.getPosition());
        if (position==null){
            position = new Position();
            position.setName(importDTO.getPosition());
            position=this.positionRepository.save(position);
        }
        employee.setPosition(position);
        employee = this.employeeRepository.save(employee);
        //position.getEmployees().add(employee);
        //this.positionRepository.save(position);

    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {

    }
}

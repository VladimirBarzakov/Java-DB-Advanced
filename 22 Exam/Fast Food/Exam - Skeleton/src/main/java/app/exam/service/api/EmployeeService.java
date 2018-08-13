package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface EmployeeService {
    void create(EmployeeJSONImportDTO importDTO);
    void createMany(EmployeeJSONImportDTO[] importDTO);
}

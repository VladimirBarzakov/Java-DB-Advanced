package app.retake.repositories;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    @Query("SELECT new app.retake.domain.dto.AnimalsJSONExportDTO(p.ownerName,a.name, a.age, p.serialNumber, p.registrationDate) " +
            "from Animal a join a.passport as p where p.ownerPhoneNumber = :phoneNumber order by a.age asc, p.serialNumber asc")
    List<AnimalsJSONExportDTO> getAllAnimalsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

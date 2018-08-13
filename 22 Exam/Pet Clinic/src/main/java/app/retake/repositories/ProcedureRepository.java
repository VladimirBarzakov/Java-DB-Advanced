package app.retake.repositories;

import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository  extends JpaRepository<Procedure, Integer>{

    @Query("select p.animalAids from Procedure p join p.animalAids where p.id = :procedure")
    List<AnimalAid> getAllAidts(@Param("procedure") int procedure);
}

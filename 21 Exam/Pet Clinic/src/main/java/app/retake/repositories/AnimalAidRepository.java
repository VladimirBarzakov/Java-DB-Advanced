package app.retake.repositories;

import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid, Integer>{

    List<AnimalAid> findByName(String name);

    @Query("SELECT a from AnimalAid a where :procedure member of a.procedures")
    List<AnimalAid> findAllByProcedures(@Param("procedure") Procedure procedure);
}

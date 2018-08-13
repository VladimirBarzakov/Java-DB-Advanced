package judgeSystem.repositories;

import judgeSystem.domain.entities.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long>{

    Strategy findOneByName(String name);
}

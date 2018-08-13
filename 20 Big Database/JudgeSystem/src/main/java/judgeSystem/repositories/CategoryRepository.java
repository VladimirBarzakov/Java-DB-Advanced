package judgeSystem.repositories;

import judgeSystem.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findOneByNameAndCategory(String name, Category category);

    Category findOneByName(String name);
}

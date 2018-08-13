package app.exam.repository;

import app.exam.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{

    Category findOneByName(String name);

    List<Category> findAllByNameIn(List<String> strings);

}

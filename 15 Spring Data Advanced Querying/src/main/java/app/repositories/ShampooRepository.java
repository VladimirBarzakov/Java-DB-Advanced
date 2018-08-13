package app.repositories;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo,Long>{
    List<BasicShampoo> findAllBySizeOrderByIdAsc(Size size);
    List<BasicShampoo> findAllBySizeOrLabel_IdOrderByPrice(@Param(value = "Size") Size size, @Param(value = "Label_Id") Long label_id);
    List<BasicShampoo> findAllByPriceGreaterThanOrderByPriceDesc(@Param(value = "price")BigDecimal price);
    Long countAllByPriceLessThan(BigDecimal price);
    @Query("SELECT s FROM BasicShampoo s join s.ingredients i where i.name in :ingredients")
    List<BasicShampoo> findAllByIngredientsIn(@Param(value = "ingredients") List<String> ingredients);
    @Query("select s from BasicShampoo s where s.ingredients.size < :count")
    List<BasicShampoo> findAllByCountOfIngredients(@Param(value = "count") Integer count);
}

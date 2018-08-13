package app.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient,Long>{
    List<BasicIngredient> findAllByNameStartsWith(String param);

    List<BasicIngredient> findAllByNameIn(List<String> names);

    @Query("UPDATE BasicIngredient i set i.price=i.price" +
            "*(1.0+:increase)")
    @Modifying
    @Transactional
    void IncreasePriceBy(@Param(value ="increase" ) double increase);

    @Query("UPDATE BasicIngredient i set i.price=i.price*2 where i.name in :ingredients")
    @Modifying
    @Transactional
    void IncreasePriceByName(@Param(value ="ingredients" ) List<String> ingredients);

    @Query("DELETE from BasicIngredient i where i.name in :ingredients")
    @Modifying
    @Transactional
    void deleteByName(@Param(value ="ingredients" ) List<String> ingredients);
}

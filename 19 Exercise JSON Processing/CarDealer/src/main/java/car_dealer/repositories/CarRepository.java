package car_dealer.repositories;

import car_dealer.entity.dto.view.CarViewModel;
import car_dealer.entity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

    @Query("select c from Car c order by c.id desc")
    List<Car> getLastInserted();

    @Query("SELECT new car_dealer.entity.dto.view.CarViewModel(c.id,c.make,c.model,c.travelledDistance) from Car c where c.make = :make order by c.model,c.travelledDistance desc")
    List<CarViewModel> getAllFromMake(@Param("make") String make);
}

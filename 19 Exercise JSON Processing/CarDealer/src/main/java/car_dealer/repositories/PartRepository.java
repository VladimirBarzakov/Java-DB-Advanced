package car_dealer.repositories;

import car_dealer.entity.dto.view.carAndPartsView.PartViewModel;
import car_dealer.entity.models.Car;
import car_dealer.entity.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer>{

    @Query("select new car_dealer.entity.dto.view.carAndPartsView.PartViewModel(p.name,p.price) from Part as p where :car member p.cars")
    List<PartViewModel> getAllByCar(@Param("car") Car car);
}

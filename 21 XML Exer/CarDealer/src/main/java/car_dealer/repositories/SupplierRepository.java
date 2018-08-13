package car_dealer.repositories;

import car_dealer.entity.dto.view.SupplierViewModel;
import car_dealer.entity.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer>{

    @Query("SELECT new car_dealer.entity.dto.view.SupplierViewModel(s.id,s.name,count(p.id) ) from Supplier as s join s.parts as p where s.isImporter=true group by s.id ")
    List<SupplierViewModel> getAllLocals();
}

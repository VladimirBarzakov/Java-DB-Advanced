package car_dealer.repositories;

import car_dealer.entity.models.Car;
import car_dealer.entity.models.Customer;
import car_dealer.entity.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer>{

    List<Sale> getAllByCustomer(Customer customer);

    @Query("select s.car from Sale as s where s = :sale")
    Car getCarBySale(@Param("sale") Sale sale);

    @Query("select s.customer from Sale as s where s = :sale")
    Customer getCustomerBySale(@Param("sale") Sale sale);
}

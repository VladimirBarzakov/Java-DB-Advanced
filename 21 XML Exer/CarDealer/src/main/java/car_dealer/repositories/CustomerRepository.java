package car_dealer.repositories;

import car_dealer.entity.dto.view.OrderedCustomerViewModel;
import car_dealer.entity.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    @Query("select new car_dealer.entity.dto.view.OrderedCustomerViewModel(c.id,c.name,c.birthDate,c.isYoungDriver) " +
            "from Customer c inner join Sale s on c.id=s.customer.id " +
            "group by c.id " +
            "order by c.birthDate asc, c.isYoungDriver desc ")
    List<OrderedCustomerViewModel> getAllOrderedCustomers();
    @Query("select c " +
            "from Customer c inner join Sale s on c.id=s.customer.id " +
            "group by c.id " +
            "order by count(s.id) desc")
    List<Customer> getAllOrderedCustomersByCountOrder();
}

package app.repositories;

import app.models.Order;
import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

    Order findByUser(User user);
}

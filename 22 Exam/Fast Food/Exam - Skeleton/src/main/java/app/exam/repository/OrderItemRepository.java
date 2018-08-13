package app.exam.repository;

import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

    @Query("SELECT new app.exam.domain.dto.xml.MostPopularItemDTO(o.item.name,SUM(o.item.price*o.quantity), sum(o.quantity)) from OrderItem as o join o.item where o.item.category = :category " +
            "group by o.item order by SUM(o.item.price*o.quantity) desc ")
    List<MostPopularItemDTO> findMostSelledItemByCategory(@Param("category") Category category);

}

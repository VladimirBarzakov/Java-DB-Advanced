package app.services.order;

import app.models.Game;
import app.models.Order;
import app.models.User;
import app.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addItem(Game game, User loggedUser) {
        Order order = this.orderRepository.findByUser(loggedUser);
        List<Game> orderGames= order.getGames();
        orderGames.add(game);
        order.setGames(orderGames);
        this.orderRepository.save(order);
        System.out.println(String.format("%s added to cart.",game.getTitle()));
    }

    @Override
    public boolean isAlreadyInShoppingCart(String title, User loggedUser) {
        if (this.orderRepository.findByUser(loggedUser).getGames().
                stream().noneMatch(x -> x.getTitle().
                equals(title))){
            return false;
        }
        return true;
    }

    @Override
    public void removeItem(Game game, User loggedUser) {
        Order order = this.orderRepository.findByUser(loggedUser);
        List<Game> orderGames= order.getGames();
        orderGames=orderGames.stream().filter(x->!x.getTitle().equals(game.getTitle())).collect(Collectors.toList());
        order.setGames(orderGames);
        this.orderRepository.save(order);
        System.out.println(String.format("%s removed from cart.",game.getTitle()));
    }

    @Override
    public void initiateOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        this.orderRepository.save(order);
    }

    @Override
    public List<Game> clearShoppingCart(User loggedUser) {
        Order order = this.orderRepository.findByUser(loggedUser);
        List<Game> orderedGames = new ArrayList<>(order.getGames());
        order.setGames(new ArrayList<>());
        this.orderRepository.save(order);
        return orderedGames;
    }
}

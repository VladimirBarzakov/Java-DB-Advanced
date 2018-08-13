package app.services.order;

import app.models.Game;
import app.models.User;

import java.util.List;

public interface OrderService {

    void addItem(Game game,User loggedUser);

    boolean isAlreadyInShoppingCart(String title, User loggedUser);

    void removeItem(Game game, User loggedUser);

    void initiateOrder(User user);

    List<Game> clearShoppingCart(User loggedUser);
}

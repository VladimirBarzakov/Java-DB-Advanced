package app.services.game;

import app.models.Game;
import app.models.User;

import java.util.List;

public interface GameService {
    void registerGame(String title, String price, String size, String trailer,
                      String thumbnailURL, String description, String releaseDate);

    void editGame(int id, String[] args);

    void deleteGame(int id);

    void getAllGameDto();

    void detailsGame(String title);

    void getAllGamesByUser(User user);

    boolean isGameAlreadyOwned(String title, User loggedUser);

    boolean existTitle(String titleAddRequest);

    Game getGameByTitle(String titleAddRequest);

    void addOwner(List<Game> games, User loggedUser);
}

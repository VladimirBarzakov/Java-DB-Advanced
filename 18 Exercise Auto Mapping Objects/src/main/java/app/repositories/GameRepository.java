package app.repositories;

import app.models.Game;
import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
    Game getById(int id);

    List<Game> getAllByTitle(String title);

    List<Game> getAllByOwners(List<User> owners);

}

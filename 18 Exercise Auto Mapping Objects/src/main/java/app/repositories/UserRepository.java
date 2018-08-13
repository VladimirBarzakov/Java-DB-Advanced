package app.repositories;


import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u.email from User u")
    List<String> getEmails();

    User getByEmail(String email);


    @Query("SELECT u FROM User u where u.isAdministrator = :param")
    List<User> findUserByAdministratorEquals(@Param(value = "param") boolean param);
}

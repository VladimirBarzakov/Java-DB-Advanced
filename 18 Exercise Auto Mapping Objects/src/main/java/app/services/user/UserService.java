package app.services.user;

import app.models.User;

public interface UserService {

    User registerUser(String email, String password, String confirmPassword, String fullName);

    User loginUser(String email, String password);

    Boolean isEmailUnique(String email);

    boolean authenticateAdmin(User loggedUser);

    boolean validateUser(User user);
}

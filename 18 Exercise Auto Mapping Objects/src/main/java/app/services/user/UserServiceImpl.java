package app.services.user;

import app.models.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password, String confirmPassword, String fullName) {
        boolean validRegisterRequest = true;
        validRegisterRequest =  validateEmail(email);
        if (!validRegisterRequest){
            System.out.println("Incorrect email!");
            return null;
        }
        validRegisterRequest=isEmailUnique(email);
        if(!validRegisterRequest){
            System.out.println("Email already registered!");
            return null;
        }

        validRegisterRequest= validatePassword(password);
        if (!validRegisterRequest){
            System.out.println("Incorrect password!");
            return null;
        }

        validRegisterRequest=password.equals(confirmPassword);
        if (!validRegisterRequest){
            System.out.println("Password and ConfirmPassword do not match!");
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        if (this.userRepository.count()==0){
            user.setAdministrator(true);
        }
        this.userRepository.save(user);
        System.out.println(String.format("%s was registered",fullName));
        return this.userRepository.getByEmail(user.getEmail());
    }

    @Override
    public User loginUser(String email, String password) {
        User user=this.userRepository.getByEmail(email);
        if (user==null || !user.getPassword().equals(password)){
            System.out.println("Incorrect username / password");
            return null;
        }
        System.out.println(String.format("Successfully logged in %s",user.getFullName()));
        return user;
    }

    @Override
    public Boolean isEmailUnique(String email) {
        List<String> registeredEmails = this.userRepository.getEmails();
        for (String registeredEmail : registeredEmails) {
            if(registeredEmail.equals(email)){

                return false;
            }
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.length()>=6 &&
                ContainsDigit(password) &&
                ContainsUpperCase(password) &&
                ContainsLowerCase(password)){
            return true;
        }
        return false;
    }

    private boolean ContainsLowerCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private boolean ContainsUpperCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private boolean ContainsDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private boolean validateEmail(String email) {
        if (!email.contains("@") ||
                (email.indexOf('@')!=email.lastIndexOf('@')) ||
                !email.contains(".")){

            return false;
        }
        return true;
    }

    @Override
    public boolean authenticateAdmin(User loggedUser) {
        List<User> admins = this.userRepository.findUserByAdministratorEquals(true);
        if (admins.isEmpty()) {
            System.out.println("No admins in database");
            return false;
        }
        for (User admin : admins) {
            if (loggedUser.getId() == admin.getId() &&
                    loggedUser.getEmail().equals(admin.getEmail()) &&
                    loggedUser.getPassword().equals(admin.getPassword()) &&
                    loggedUser.getFullName().equals(admin.getFullName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateUser(User loggedUser) {
        User userDB = this.userRepository.getByEmail(loggedUser.getEmail());
        if (userDB!=null &&
                loggedUser.getId()==userDB.getId() &&
                loggedUser.getEmail().equals(userDB.getEmail()) &&
                loggedUser.getPassword().equals(userDB.getPassword()) &&
                loggedUser.getFullName().equals(userDB.getFullName()) ){
            return true;
        } else{
            return false;
        }


    }
}

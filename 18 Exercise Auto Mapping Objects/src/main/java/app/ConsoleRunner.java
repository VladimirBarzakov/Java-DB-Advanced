package app;


import app.models.Game;
import app.models.User;
import app.services.game.GameServiceImpl;
import app.services.order.OrderServiceImpl;
import app.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner{

    private GameServiceImpl gameService;
    private UserServiceImpl userService;
    private OrderServiceImpl orderService;

    @Autowired
    public ConsoleRunner(GameServiceImpl gameService, UserServiceImpl userService, OrderServiceImpl orderService) {
        this.gameService = gameService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... strings) throws Exception {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User loggedUser=null;
        while (true){
            System.out.println("Enter your command:");
            String[] tokens = reader.readLine().trim().split("\\|");

            String command = tokens[0];
            String email;
            String password;
            switch (command){
                case "RegisterUser":
                    email=tokens[1];
                    password=tokens[2];
                    String confirmPassword = tokens[3];
                    String fullName = tokens[4];
                    User user = this.userService.registerUser(email,password,confirmPassword,fullName);
                    if (user!=null){
                        this.orderService.initiateOrder(user);
                    }
                    break;
                case "LoginUser":
                    email=tokens[1];
                    password=tokens[2];
                    loggedUser = this.userService.loginUser(email,password);
                    break;
                case "Logout":
                    if(loggedUser==null){
                        System.out.println("Cannot log out. No user was logged in.");
                    } else{
                        System.out.println(String.format("User %s successfully logged out",loggedUser.getFullName()));
                        loggedUser=null;
                    }
                    break;
                case "AddGame":
                    String title = tokens[1];
                    String price = tokens[2];
                    String size = tokens[3];
                    String trailer = tokens[4];
                    String thumbnailURL = tokens[5];
                    String description = tokens[6];
                    String releaseDate = tokens[7];

                    if (loggedUser==null||!this.userService.authenticateAdmin(loggedUser)){
                        System.out.println("No administrator logged in!");
                    } else{
                        this.gameService.registerGame(title,price,size,trailer,thumbnailURL,description,releaseDate);
                    }
                    break;
                case "EditGame":
                    if (loggedUser==null||!this.userService.authenticateAdmin(loggedUser)){
                        System.out.println("No administrator logged in!");
                    } else{
                        int id = Integer.parseInt(tokens[1]);
                        String[] args = Arrays.stream(tokens).skip(2).toArray(String[]::new);
                        this.gameService.editGame(id,args);
                    }
                    break;
                case "DeleteGame":
                    if (loggedUser==null||!this.userService.authenticateAdmin(loggedUser)){
                        System.out.println("No administrator logged in!");
                    } else{
                        int id = Integer.parseInt(tokens[1]);
                        this.gameService.deleteGame(id);
                    }
                    break;
                case "AllGame":
                    this.gameService.getAllGameDto();
                    break;
                case "DetailsGame":
                    String titleDetailRequest = tokens[1];
                    this.gameService.detailsGame(titleDetailRequest);
                    break;
                case "OwnedGame":
                    if (loggedUser==null||!this.userService.validateUser(loggedUser)){
                        System.out.println("No user logged in!");
                    } else {
                        this.gameService.getAllGamesByUser(loggedUser);
                    }
                    break;
                case "AddItem":
                    String titleAddRequest = tokens[1];
                    if (loggedUser==null||!this.userService.validateUser(loggedUser)){
                        System.out.println("No user logged in!");
                    } else if(!this.gameService.existTitle(titleAddRequest)){
                        System.out.println("No such game!");
                    }else if(this.gameService.isGameAlreadyOwned(titleAddRequest,loggedUser) ||
                            this.orderService.isAlreadyInShoppingCart(titleAddRequest,loggedUser)) {
                        System.out.println("User already have that game or the game is already in his shopping cart");
                    } else{
                        Game game = this.gameService.getGameByTitle(titleAddRequest);
                        this.orderService.addItem(game,loggedUser);
                    }
                    break;
                case "RemoveItem":
                    String titleRemoveRequest = tokens[1];
                    if (loggedUser==null||!this.userService.validateUser(loggedUser)){
                        System.out.println("No user logged in!");
                    } else if(!this.gameService.existTitle(titleRemoveRequest)){
                        System.out.println("No such game!");
                    }else if(!this.orderService.isAlreadyInShoppingCart(titleRemoveRequest,loggedUser)) {
                        System.out.println("This game is not in your shopping cart!");
                    } else{
                        Game game = this.gameService.getGameByTitle(titleRemoveRequest);
                        this.orderService.removeItem(game,loggedUser);
                    }
                    break;
                case "BuyItem":
                    if (loggedUser==null||!this.userService.validateUser(loggedUser)){
                        System.out.println("No user logged in!");
                    } else{
                        List<Game> games = this.orderService.clearShoppingCart(loggedUser);
                        this.gameService.addOwner(games,loggedUser);
                    }
                    break;
                case "End":
                    return;
                default:
                    System.out.println("Unknown command!");
                    break;

            }
        }

    }
}

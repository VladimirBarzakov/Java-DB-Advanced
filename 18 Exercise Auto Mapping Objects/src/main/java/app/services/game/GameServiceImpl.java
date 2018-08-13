package app.services.game;

import app.dto.GameDetailDto;
import app.dto.GameDto;
import app.models.Game;
import app.models.User;
import app.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService{
    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void registerGame(String title, String price, String size, String trailer, String thumbnailURL, String description, String releaseDate) {
        boolean isValidUserRequest = true;
        if (!this.gameRepository.getAllByTitle(title).isEmpty()){
            System.out.println("You cannot add game twice!");
            return;
        }
        if (!validateTitle(title)){
            System.out.println("Invalid title!");
            return;
        }
        BigDecimal priceBigNum = isValidPrice(price);
        if (priceBigNum==null){
            System.out.println("Invalid price!");
            return;
        }
        if (!isValidSize(size)){
            System.out.println("Invalid size!");
            return;
        }
        Double sizeDouble = Double.parseDouble(size);
        String trailerURLforDB  = validateTrailerURLandGetValue(trailer);
        if (trailerURLforDB.equals("")){
            System.out.println("Invalid trailer");
            return;
        }
        if (!validateThumbnailURL(thumbnailURL)){
            System.out.println("Invalid thumbnailURL");
            return;
        }
        if (description.length()<20){
            System.out.println("Invalid description");
            return;
        }
        Date date = validateDate(releaseDate);
        if (date==null){
            System.out.println("Invalid date!");
        }
        Game game = new Game();
        game.setTitle(title);
        game.setPrice(priceBigNum);
        game.setSize(sizeDouble);
        game.setTrailer(trailerURLforDB);
        if (!thumbnailURL.isEmpty()){
            game.setImageThumbnail(thumbnailURL);
        }
        game.setDescription(description);
        game.setReleaseDate(date);
        this.gameRepository.save(game);
        System.out.println(String.format("Added %s",game.getTitle()));
    }


    @Override
    public void editGame(int id, String[] args) {
        Game game = gameRepository.getById(id);
        if (game==null){
            System.out.println("There is no game with given id!");
            return;
        }
        for (String arg : args) {
            String[] tokens = arg.split("=");
            String field = tokens[0];
            String value = tokens[1];
            switch (field){
                case "title":
                    if (!validateTitle(value)){
                        System.out.println("Invalid title!");
                        return;
                    }
                    game.setTitle(value);
                    break;
                case "price":
                    BigDecimal priceBigNum = isValidPrice(value);
                    if (priceBigNum==null){
                        System.out.println("Invalid price!");
                        return;
                    }
                    game.setPrice(priceBigNum);
                    break;
                case "size":
                    if (!isValidSize(value)){
                        System.out.println("Invalid size!");
                        return;
                    }
                    Double sizeDouble = Double.parseDouble(value);
                    game.setSize(sizeDouble);
                    break;
                case "trailer":
                    String trailerURLforDB  = validateTrailerURLandGetValue(value);
                    if (trailerURLforDB.equals("")){
                        System.out.println("Invalid trailer");
                        return;
                    }
                    game.setTrailer(trailerURLforDB);
                    break;
                case "thubnailURL":
                    if (!validateThumbnailURL(value)){
                        System.out.println("Invalid thumbnailURL");
                        return;
                    }
                    game.setImageThumbnail(value);
                    break;
                case "description":
                    if (value.length()<20){
                        System.out.println("Invalid description");
                        return;
                    }
                    game.setDescription(value);
                    break;
                case "releaseDate":
                    Date date = validateDate(value);
                    if (date==null){
                        System.out.println("Invalid date!");
                    }
                    game.setReleaseDate(date);
                    break;
            }
        }
        this.gameRepository.save(game);
        System.out.println(String.format("Edited %s",game.getTitle()));
    }

    @Override
    public void deleteGame(int id) {
        if (!this.gameRepository.exists(id)){
            System.out.println("There is no game with given id!");
            return;
        }
        String gameTitle=this.gameRepository.getById(id).getTitle();
        this.gameRepository.delete(id);
        System.out.println(String.format("Deleted %s",gameTitle));
    }

    @Override
    public void getAllGameDto() {
        ModelMapper mapper = new ModelMapper();
        List<GameDto> gameDtos = this.gameRepository.findAll().stream().map(x->mapper.map(x,GameDto.class)).collect(Collectors.toList());

        for (GameDto gameDto : gameDtos) {
            System.out.println(String.format("%s %.2f",gameDto.getTitle(),gameDto.getPrice()));
        }
    }

    @Override
    public void getAllGamesByUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        ModelMapper mapper = new ModelMapper();
        List<GameDto> gameDtos = this.gameRepository.getAllByOwners(users).stream().map(x->mapper.map(x,GameDto.class)).collect(Collectors.toList());
        for (GameDto gameDto : gameDtos) {
            System.out.printf("%s%n",gameDto.getTitle());
        }
    }

    @Override
    public void detailsGame(String title) {
        ModelMapper mapper = new ModelMapper();
        List<GameDetailDto> gameDetailDtoList = this.gameRepository.getAllByTitle(title).stream().map(x->mapper.map(x,GameDetailDto.class)).collect(Collectors.toList());

        for (GameDetailDto gameDetailDto : gameDetailDtoList) {
            System.out.printf("Title: %s%n",gameDetailDto.getTitle());
            System.out.printf("Price: %.2f%n",gameDetailDto.getPrice());
            System.out.printf("Description: %s%n",gameDetailDto.getDescription());
            System.out.printf("Release date: %s%n",gameDetailDto.getReleaseDate().toString().substring(0,9));
        }
    }

    @Override
    public boolean isGameAlreadyOwned(String title, User loggedUser) {
        List<User> owner = new ArrayList<>();
        owner.add(loggedUser);
        if (this.gameRepository.getAllByOwners(owner).
                stream().noneMatch(x -> x.getTitle().
                equals(title))){
            return false;
        }
        return true;
    }


    private Date validateDate(String releaseDate) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
            date=formatter.parse(releaseDate);
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return date;
    }

    private boolean validateThumbnailURL(String thumbnailURL) {
        if (thumbnailURL.isEmpty() ||
                thumbnailURL.startsWith("http://") ||
                thumbnailURL.startsWith("https://")){
            return true;
        }
        return false;
    }

    private String validateTrailerURLandGetValue(String trailer) {
        if (!trailer.startsWith("https://www.youtube.com/watch?v=")){
            return "";
        }
        String result = trailer.replace("https://www.youtube.com/watch?v=","");
        if (result.length()!=11){
            return "";
        }
        return result;
    }

    private boolean isValidSize(String size) {
        Double sizeDouble;
        try {
            sizeDouble=Double.parseDouble(size);
        } catch (Exception e){
            return false;
        }
        if (sizeDouble<=0 || (size.contains(".") && size.indexOf(".")!=size.length()-2 ) ){
            return false;
        }
        return true;
    }

    private BigDecimal isValidPrice(String price) {
        BigDecimal priceBigNum;
        try {
            priceBigNum=new BigDecimal(price);
        } catch (Exception e){
            return null;
        }
        if (priceBigNum.compareTo(new BigDecimal("0"))<=0 || price.lastIndexOf(".")<price.length()-3){
            return null;
        }
        return priceBigNum;
    }

    private boolean validateTitle(String title) {
        if (Character.isUpperCase(title.charAt(0)) && title.length()>=3 && title.length()<=100){
            return true;
        }
        return false;
    }

    @Override
    public boolean existTitle(String titleAddRequest) {
        return !this.gameRepository.getAllByTitle(titleAddRequest).isEmpty();
    }

    @Override
    public Game getGameByTitle(String titleAddRequest) {
        return this.gameRepository.getAllByTitle(titleAddRequest).get(0);
    }

    @Override
    public void addOwner(List<Game> games, User loggedUser) {

        for (Game game : games) {
            List<User> owners = this.gameRepository.getById(game.getId()).getOwners();
            owners.add(loggedUser);
            game.setOwners(owners);
            this.gameRepository.save(game);
        }
        System.out.println("Successfully bought games:");
        for (Game game : games) {
            System.out.printf("  -%s%n",game.getTitle());
        }
    }
}

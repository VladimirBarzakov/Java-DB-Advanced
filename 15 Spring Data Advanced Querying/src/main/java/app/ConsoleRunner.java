package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.shampoos.BasicShampoo;
import app.repositories.IngredientRepository;
import app.repositories.LabelRepository;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner{

    private IngredientRepository ingredientRepository;
    private LabelRepository labelRepository;
    private ShampooRepository shampooRepository;

    @Autowired
    public ConsoleRunner(IngredientRepository ingredientRepository, LabelRepository labelRepository, ShampooRepository shampooRepository) {
        this.ingredientRepository = ingredientRepository;
        this.labelRepository = labelRepository;
        this.shampooRepository = shampooRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        //getShampoosBySize(Size.LARGE);
        //getAllShampoosBySizeOrLabelId(Size.MEDIUM,10L);
        //getAllShampoosByPrice(new BigDecimal("5"));
        //getAllIngredientsWithNameStarts("M");

        //List<String> list = new ArrayList<>();
        //list.add("Lavender");
        //list.add("Herbs");
        //list.add("Apple");
        //list.add("Disturbed");
        //getExistingIngredientsFromSet(list);

        //System.out.println(this.shampooRepository.countAllByPriceLessThan(new BigDecimal("8.15")));

        //List<String> list = new ArrayList<>();
        //

        //list.add("Mineral-Colagen");
        //printShampooResult(this.shampooRepository.findAllByIngredientsIn(list));

        //printShampooResult(this.shampooRepository.findAllByCountOfIngredients(2));

        List<String> list = new ArrayList<>();
        list.add("Nettle");

        //this.ingredientRepository.IncreasePriceByName(list);
        this.ingredientRepository.deleteByName(list);

        //this.ingredientRepository.IncreasePriceBy(0.33);







    }

    private void getExistingIngredientsFromSet(List<String> list) {
        printIngredientResult(this.ingredientRepository.findAllByNameIn(list));
    }

    private void getAllIngredientsWithNameStarts(String letter) {
        printIngredientResult(this.ingredientRepository.findAllByNameStartsWith(letter));
    }

    private void getAllShampoosByPrice(BigDecimal price) {
        printShampooResult(this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price));
    }

    private void getAllShampoosBySizeOrLabelId(Size size, long id) {
        printShampooResult(this.shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(size,id));
    }

    private void getShampoosBySize(Size size) {
        printShampooResult(this.shampooRepository.findAllBySizeOrderByIdAsc(size));
    }

    private void printShampooResult(List<BasicShampoo> list){
        list.forEach(x->{
            System.out.printf("%s %s %.2flv.%n",x.getBrand(),x.getSize(),x.getPrice());
        });
    }

    private void printIngredientResult(List<BasicIngredient> list){
        list.forEach(x->{
            System.out.printf("%s %n",x.getName());
        });
    }
}

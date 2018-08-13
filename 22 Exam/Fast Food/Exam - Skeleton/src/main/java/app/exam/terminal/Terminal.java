package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIOImpl;
import app.exam.io.interfaces.FileIOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private FileIOImpl fileIO;

    @Autowired
    private ConsoleIOImpl consoleIO;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private EmployeesController employeesController;

    @Autowired
    private ItemsController itemsController;

    @Autowired
    private OrdersController ordersController;

    @Override
    public void run(String... args) throws Exception {

        //this.consoleIO.write(this.employeesController.importDataFromJSON(this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON)));
        //this.consoleIO.write(this.itemsController.importDataFromJSON(this.fileIO.read(Config.ITEMS_IMPORT_JSON)));
        //this.consoleIO.write(this.ordersController.importDataFromXML(this.fileIO.read(Config.ORDERS_IMPORT_XML)));
        //this.consoleIO.write(this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"));
        List<String> categories = Arrays.asList("Chicken", "Toys", "Drinks");
        this.consoleIO.write(this.categoryController.getCategoriesWithMostPopularItemsSorted(categories));
    }
}

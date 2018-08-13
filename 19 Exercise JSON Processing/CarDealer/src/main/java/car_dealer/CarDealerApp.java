package car_dealer;

import car_dealer.entity.dto.binding.*;
import car_dealer.entity.dto.view.*;
import car_dealer.entity.dto.view.carAndPartsView.CarAndPartsViewModel;
import car_dealer.entity.dto.view.carAndPartsView.CarsAndPartsListViewModel;
import car_dealer.services.*;
import car_dealer.utils.IO.IOFileMethod;
import car_dealer.utils.IO.XmlParserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@SpringBootApplication
@Component
public class CarDealerApp implements CommandLineRunner{

    private CarServiceImpl carService;
    private CustomerServiceImpl customerService;
    private PartServiceImpl partService;
    private SaleServiceImpl saleService;
    private SupplierServiceImpl supplierService;

    private final XmlParserImpl xmlParser;
    private final IOFileMethod ioFileMethod;


    private static final String CAR_FILE_PATH = "/inputXML/cars.xml";
    private static final String CUSTOMERS_FILE_PATH = "/inputXML/customers.xml";
    private static final String PARTS_FILE_PATH = "/inputXML/parts.xml";
    private static final String SUPPLIERS_FILE_PATH = "/inputXML/suppliers.xml";

    public static final String UTF8_BOM = "\uFEFF";

    @Autowired
    public CarDealerApp(CarServiceImpl carService,
                        CustomerServiceImpl customerService,
                        PartServiceImpl partService,
                        SaleServiceImpl saleService,
                        SupplierServiceImpl supplierService,
                        XmlParserImpl xmlParser,
                        IOFileMethod ioFileMethod) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;
        this.ioFileMethod = ioFileMethod;
    }

    @Override
    public void run(String... args) throws Exception {

        //seedDataBase();
        
        //orderedCustomers();
        //carsFromMake("Toyota");
        //localSuppliers();
        //carsAndParts();
        //totalSalesByCustomer();
        salesWithAppliedDiscount();

    }

    private void salesWithAppliedDiscount() {
        List<SalesViewModel> sales = this.saleService.getSales();
        SaleListViewModel saleList = new SaleListViewModel();
        saleList.setSales(sales);
        String content = this.xmlParser.serialise(saleList);
        System.out.println(content);
        this.ioFileMethod.writeToFile("sales-discounts.xml",content);
    }

    private void totalSalesByCustomer() {
        List<CustomerViewModel> customers = this.customerService.getCustomersWithOrder();
        CustomerListViewModel customerListViewModel = new CustomerListViewModel();
        customerListViewModel.setCustomers(customers);
        String content = this.xmlParser.serialise(customerListViewModel);
        System.out.println(content);
        this.ioFileMethod.writeToFile("customers-total-sales.xml",content);
    }

    private void carsAndParts() {
        List<CarAndPartsViewModel> carAndPartsViewModelList = this.carService.getCarsAndParts();
        CarsAndPartsListViewModel carsAndPartsListViewModel = new CarsAndPartsListViewModel();
        carsAndPartsListViewModel.setCarsAndParts(carAndPartsViewModelList);
        String content = this.xmlParser.serialise(carsAndPartsListViewModel);
        System.out.println(content);
        this.ioFileMethod.writeToFile("cars-and-parts.xml",content);
    }

    private void localSuppliers() {
        List<SupplierViewModel> localSuppliers = this.supplierService.getAllLocals();
        SupplierListViewModel supplierList = new SupplierListViewModel();
        supplierList.setSuppliers(localSuppliers);
        String content = this.xmlParser.serialise(supplierList);
        System.out.println(content);
        this.ioFileMethod.writeToFile("local-suppliers.xml",content);
    }

    private void carsFromMake(String make) {
        List<CarViewModel> list= this.carService.getAllWithMake(make);
        CarListViewModel carList = new CarListViewModel();
        carList.setCars(list);
        String content = this.xmlParser.serialise(carList);
        System.out.println(content);
        this.ioFileMethod.writeToFile("toyota-cars.xml",content);
    }


    private void orderedCustomers() {
        List<OrderedCustomerViewModel> customerDTOs = this.customerService.getOrderedCustomers();
        OrderedCustomerListViewModel orderedCustomerListViewModel = new OrderedCustomerListViewModel();
        orderedCustomerListViewModel.setCustomers(customerDTOs);

        String content = this.xmlParser.serialise(orderedCustomerListViewModel);
        System.out.println(content);
        this.ioFileMethod.writeToFile("ordered-customers.xml",content);
    }

    private void seedDataBase() throws ParseException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }

    private void seedSales() {
        this.saleService.seed();
    }

    private void seedCustomers() throws ParseException {
        String loaded = this.ioFileMethod.readAllData(CUSTOMERS_FILE_PATH);
        if (loaded.startsWith(UTF8_BOM)){
            loaded=loaded.substring(1);
        }
        CustomerListBindingModel customerList = this.xmlParser.deserialise(loaded,CustomerListBindingModel.class);
        List<CustomerBindingModel> customerBindingModels = customerList.getCustomers();
        this.customerService.saveDTO(customerBindingModels);
    }

    private void seedCars() {
        String loaded = this.ioFileMethod.readAllData(CAR_FILE_PATH);
        if (loaded.startsWith(UTF8_BOM)){
            loaded=loaded.substring(1);
        }
        CarsListBindingModel carList = this.xmlParser.deserialise(loaded,CarsListBindingModel.class);
        List<CarBindingModel> carBindingModels = carList.getCars();
        this.carService.saveDTO(carBindingModels);
    }

    private void seedParts() {

        String loaded = this.ioFileMethod.readAllData(PARTS_FILE_PATH);
        if (loaded.startsWith(UTF8_BOM)){
            loaded=loaded.substring(1);
        }
        PartsListBindingModel partsList = this.xmlParser.deserialise(loaded,PartsListBindingModel.class);
        List<PartsBindingModel> partsBindingModels = partsList.getParts();
        this.partService.saveDTO(partsBindingModels);
    }

    private void seedSuppliers() {

        String loaded = this.ioFileMethod.readAllData(SUPPLIERS_FILE_PATH);
        if (loaded.startsWith(UTF8_BOM)){
            loaded=loaded.substring(1);
        }
        SupplierListBindingModel supplierList = this.xmlParser.deserialise(loaded,SupplierListBindingModel.class);
        List<SupplierBindingModel> supplierBindingModels = supplierList.getSuppliers();
        this.supplierService.saveDTO(supplierBindingModels);
    }


}

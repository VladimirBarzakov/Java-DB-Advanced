package car_dealer.services;

import car_dealer.entity.dto.view.CarSaleViewModel;
import car_dealer.entity.dto.view.CarViewModel;
import car_dealer.entity.dto.view.SalesViewModel;
import car_dealer.entity.models.Car;
import car_dealer.entity.models.Customer;
import car_dealer.entity.models.Sale;
import car_dealer.repositories.CarRepository;
import car_dealer.repositories.CustomerRepository;
import car_dealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SaleServiceImpl {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void seed() {
        List<Customer> customers = this.customerRepository.findAll();
        List<Car> cars = this.carRepository.findAll();
        Random random = new Random();
        Double d= new Double(this.carRepository.count()*0.6);
        int carListSize = d.intValue();
        List<Car> carList = new ArrayList<>();
        while (carList.size()<carListSize){
            int index= random.nextInt(cars.size());
            carList.add(cars.get(index));
            cars.remove(index);
        }
        List<Sale> sales = new ArrayList<>();
        double[] discountArray = {0d,0.05d,0.1d,0.15d,0.2d,0.3d,0.4d,0.5d};

        while (carList.size()>0){
            Sale sale = new Sale();
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setCar(carList.get(carList.size()-1));
            carList.remove(carList.size()-1);
            sale.setDiscount(discountArray[random.nextInt(discountArray.length)]);
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);


    }

    public List<SalesViewModel> getSales() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SalesViewModel> list = new ArrayList<>();

        for (Sale sale : sales) {
            Car car = this.saleRepository.getCarBySale(sale);
            Customer customer = this.saleRepository.getCustomerBySale(sale);
            CarSaleViewModel carModel = new CarSaleViewModel(car.getMake(),car.getModel(),car.getTravelledDistance());
            SalesViewModel model = new SalesViewModel(carModel,customer.getName(),sale.getDiscount(),car.getPrice());
            list.add(model);
        }
        return list;
    }
}

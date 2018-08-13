package car_dealer.services;

import car_dealer.entity.dto.binding.CustomerBindingModel;
import car_dealer.entity.dto.view.CustomerViewModel;
import car_dealer.entity.dto.view.OrderedCustomerViewModel;
import car_dealer.entity.models.Customer;
import car_dealer.entity.models.Sale;
import car_dealer.repositories.CustomerRepository;
import car_dealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository=saleRepository;
    }

    public void saveDTO(List<CustomerBindingModel> customerBindingModels) throws ParseException {
        List<Customer> customers = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
        for (CustomerBindingModel customerModel : customerBindingModels) {
            Customer customer = new Customer();
            customer.setName(customerModel.getName());
            Date date = df.parse(customerModel.getBirthDate());
            customer.setBirthDate(date);
            customer.setYoungDriver(customerModel.isYoungDriver());
            customers.add(customer);
        }
        this.customerRepository.saveAll(customers);
    }

    public List<OrderedCustomerViewModel> getOrderedCustomers() {

       return this.customerRepository.getAllOrderedCustomers();

    }

    public List<CustomerViewModel> getCustomersWithOrder() {
        List<Customer> customers = this.customerRepository.getAllOrderedCustomersByCountOrder();
        List<CustomerViewModel> customerDTOlist = new ArrayList<>();

        for (Customer customer : customers) {
            List<Sale> sales = this.saleRepository.getAllByCustomer(customer);
            BigDecimal spentMoney= new BigDecimal(0d);
            for (Sale sale : sales) {
                spentMoney=spentMoney.add(sale.getCar().getPrice());
            }
            CustomerViewModel model = new CustomerViewModel(customer.getName(),sales.size(),spentMoney);
            customerDTOlist.add(model);
        }
        return customerDTOlist.stream().sorted((x,y)->{
            int comparator = y.getSpentMoney().compareTo(x.getSpentMoney());
            if (comparator==0 && y.getBoughtCars()>x.getBoughtCars()){
                return 1;
            }
            return comparator;
        }).collect(Collectors.toList());
    }
}

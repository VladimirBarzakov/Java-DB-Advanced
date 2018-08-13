package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.ModelParserImpl;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.repository.OrderRepository;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ModelParserImpl modelParser;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {

        Employee employee = this.employeeRepository.findOneByName(dto.getEmployee());
        if (employee==null){
            throw  new IllegalArgumentException();
        }
        List<OrderItemXMLImportDTO> orderItemsDTOs = dto.getOrderItems().getOrderItems();
        List<Item> items = new ArrayList<>();
        for (OrderItemXMLImportDTO orderItemsDTO : orderItemsDTOs) {
            Item item = this.itemsRepository.findOneByName(orderItemsDTO.getName());
            if (item==null){
                throw  new IllegalArgumentException();
            }
            items.add(item);
        }
        Order order = this.modelParser.convert(dto, Order.class);
        order.setEmployee(employee);
        order.getItems().clear();

        order = this.orderRepository.save(order);

        for (int i = 0; i < items.size(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(items.get(i));
            orderItem.setQuantity(orderItemsDTOs.get(i).getQuantity());
            this.orderItemRepository.save(orderItem);
        }
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {

        Employee employee = this.employeeRepository.findOneByName(employeeName);
        OrderType orderType1 = OrderType.valueOf(orderType);
        List<Order> orders = this.orderRepository.getAllByEmployeeAndOrderType(employee,orderType1);

        orders = orders.stream().sorted((x,y)->{
            int comparator = y.getTotalPrice().compareTo(x.getTotalPrice());
            if (comparator==0){
                int yItems = y.getItems().size();
                int xItems = x.getItems().size();
                comparator=Integer.compare(yItems,xItems);
            }
            return comparator;
        }).collect(Collectors.toList());

        List<OrderJSONExportDTO> ordersDTO = new ArrayList<>();
        for (Order order : orders) {
            OrderJSONExportDTO orderDTo = new OrderJSONExportDTO();
            orderDTo.setCustomer(order.getCustomer());

            List<ItemJSONExportDTO> itemsDTO = new ArrayList<>();
            for (OrderItem orderItem : order.getItems()
                    .stream()
                    .sorted((x,y)->Integer.compare(x.getId(),y.getId()))
                    .collect(Collectors.toList())) {
                ItemJSONExportDTO itemDTO = new ItemJSONExportDTO();
                itemDTO.setName(orderItem.getItem().getName());
                itemDTO.setPrice(orderItem.getItem().getPrice());
                itemDTO.setQuantity(orderItem.getQuantity());
                itemsDTO.add(itemDTO);
            }
            orderDTo.setItems(itemsDTO);
            ordersDTO.add(orderDTo);
        }
        EmployeeOrdersJSONExportDTO export = new EmployeeOrdersJSONExportDTO();
        export.setEmployeeName(employeeName);
        export.setOrders(ordersDTO);

        return export;
    }
}

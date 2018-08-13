package app.exam.controller;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.ValidationUtil;
import app.exam.parser.XMLParser;
import app.exam.service.api.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private XMLParser parser;
    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private JSONParser jsonParser;

    public String importDataFromXML(String xmlContent) {

        OrderWrapperXMLImportDTO ordersWrapper = this.parser.read(OrderWrapperXMLImportDTO.class, xmlContent);
        StringBuilder builder = new StringBuilder();

        List<OrderXMLImportDTO> orderDTOs = ordersWrapper.getOrders();
        outerLoop:
        for (OrderXMLImportDTO orderDTO : orderDTOs) {

            if (!this.validationUtil.isValid(orderDTO)){
                builder.append(String.format("Error: Invalid data.%n"));
                continue;
            }
            List<OrderItemXMLImportDTO> orderItemDTOs = orderDTO.getOrderItems().getOrderItems();
            for (OrderItemXMLImportDTO orderItemDTO : orderItemDTOs) {
                if (!this.validationUtil.isValid(orderItemDTO)){
                    builder.append(String.format("Error: Invalid data.%n"));
                    continue outerLoop;
                }
            }
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                this.orderService.create(orderDTO);
                builder.append(String.format("Order for %s on %s added.%n",
                        orderDTO.getCustomer(),
                        dateFormat.format(orderDTO.getDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                builder.append(String.format("Error: Invalid data.%n"));
            }
        }

        return builder.toString().trim();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {

        EmployeeOrdersJSONExportDTO dto = this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);

        try {
            return this.jsonParser.write(dto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }
}

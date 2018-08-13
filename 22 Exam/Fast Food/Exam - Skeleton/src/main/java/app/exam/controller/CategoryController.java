package app.exam.controller;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.ValidationUtil;
import app.exam.parser.XMLParser;
import app.exam.service.api.CategoryServiceImpl;
import app.exam.service.api.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CategoryController {

    private XMLParser xmlParser;
    private CategoryServiceImpl categoryService;
    private ValidationUtil validationUtil;

    @Autowired
    public CategoryController(XMLParser xmlParser,
                              CategoryServiceImpl categoryService,
                              ValidationUtil validationUtil) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.validationUtil = validationUtil;
    }

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames){

        CategoriesFrequentItemsXMLExportDTO exportDTO =
                this.categoryService.getCategoriesWithMostPopularItems(categoryNames);

        String result = this.xmlParser.write(exportDTO);

        return result;
    }
}

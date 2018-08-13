package app.exam.service.api;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.repository.CategoryRepository;
import app.exam.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {

        List<Category> categories = this.categoryRepository.findAllByNameIn(categoryNames);

        List<CategoryExportDTO> categoryExportDTOS = new ArrayList<>();

        for (Category category : categories) {
            MostPopularItemDTO dto = this.orderItemRepository.findMostSelledItemByCategory(category).get(0);
            CategoryExportDTO categoryDTO = new CategoryExportDTO();
            categoryDTO.setName(category.getName());
            categoryDTO.setMostPopularItem(dto);
            categoryExportDTOS.add(categoryDTO);
        }

        categoryExportDTOS = categoryExportDTOS.stream().sorted((x,y)->{
            int comparator = y.getMostPopularItem().getTotalMade().
                    compareTo(x.getMostPopularItem().getTotalMade());
            if (comparator==0){
                comparator = Long.compare(y.getMostPopularItem().
                        getTimesSold(),x.getMostPopularItem().getTimesSold());
            }
            return comparator;
        }).collect(Collectors.toList());
        CategoriesFrequentItemsXMLExportDTO export = new CategoriesFrequentItemsXMLExportDTO();
        export.setCategories(categoryExportDTOS);
        return export;
    }
}

package app.exam.service.api;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.ModelParserImpl;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService{

    @Autowired
    private ModelParserImpl modelParser;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {


        Item item = this.itemsRepository.findOneByName(itemJSONImportDTO.getName());
        if (item!=null){
            throw  new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findOneByName(itemJSONImportDTO.getCategory());
        if (category==null){
            category=new Category();
            category.setName(itemJSONImportDTO.getCategory());
            category=this.categoryRepository.save(category);
        }
        item = this.modelParser.convert(itemJSONImportDTO, Item.class);
        item.setCategory(category);
        item = this.itemsRepository.save(item);
    }
}

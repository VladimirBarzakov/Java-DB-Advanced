package judgeSystem.services;

import judgeSystem.domain.dto.categoryImport.CategoryImportJSON;
import judgeSystem.domain.entities.Category;
import judgeSystem.parser.ModelParser;
import judgeSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final ModelParser modelParser;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ModelParser modelParser,
                           CategoryRepository categoryRepository) {
        this.modelParser = modelParser;
        this.categoryRepository = categoryRepository;
    }

    public void importDTO(CategoryImportJSON dto) {
        Category category = this.modelParser.convert(dto, Category.class);
        if (dto.getCategory()!=null) {
            Category parent = this.modelParser.convert(dto.getCategory(), Category.class);
            parent= saveIfNotExist(parent);
            category.setCategory(parent);
        }
        category= saveIfNotExist(category);
        if (dto.getCategories().size()!=0) {
            saveSubCategories(category, dto.getCategories());
        }
    }

    private Category saveIfNotExist(Category category) {
        List<Category> categoriesDB = this.categoryRepository.findOneByNameAndCategory(
                category.getName(),
                category.getCategory());
        if (categoriesDB.size()==0){
            category=this.categoryRepository.save(category);
            return category;
        }
        return categoriesDB.get(0);
    }

    private void saveSubCategories(Category category, List<CategoryImportJSON> categories) {
        for (CategoryImportJSON categoryImportJSON : categories) {
            Category categoryChild = this.modelParser.convert(categoryImportJSON, Category.class);
            categoryChild.setCategory(category);
            categoryChild=saveIfNotExist(categoryChild);
            category.getSubCategories().add(categoryChild);
            this.categoryRepository.save(category);
            if (categoryImportJSON.getCategories().size()!=0) {
                saveSubCategories(categoryChild, categoryImportJSON.getCategories());
            }
        }
    }

}

package application.services.Category;

import application.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import application.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}

package application.services.Category;

import application.models.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    List<Category> getAllCategories();
}

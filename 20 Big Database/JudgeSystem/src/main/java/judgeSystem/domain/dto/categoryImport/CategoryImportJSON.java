package judgeSystem.domain.dto.categoryImport;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CategoryImportJSON {


    private int id;

    @Expose
    private String name;

    @Expose
    private CategoryImportJSON category;

    @Expose
    private List<CategoryImportJSON> categories;

    public CategoryImportJSON() {
        this.categories=new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryImportJSON getCategory() {
        return category;
    }

    public void setCategory(CategoryImportJSON category) {
        this.category = category;
    }

    public List<CategoryImportJSON> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryImportJSON> categories) {
        this.categories = categories;
    }
}

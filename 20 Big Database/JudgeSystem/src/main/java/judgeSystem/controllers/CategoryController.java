package judgeSystem.controllers;

import judgeSystem.domain.dto.categoryImport.CategoryImportJSON;
import judgeSystem.parser.JsonParser;
import judgeSystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    private final JsonParser jsonParser;

    @Autowired
    public CategoryController(CategoryService categoryService, JsonParser jsonParser) {
        this.categoryService = categoryService;
        this.jsonParser = jsonParser;
    }

    public String insertCategory(String content) {

        if (content==null){
            return "No content found";
        }

        StringBuilder builder = new StringBuilder();
        CategoryImportJSON[] categoriesDTO =jsonParser.read(
                CategoryImportJSON[].class,content);
        for (CategoryImportJSON categoryImportJSON : categoriesDTO) {

            this.categoryService.importDTO(categoryImportJSON);
                builder.append(String.format("Successfully import category %s and it's subcategories.%n",
                        categoryImportJSON.getName()));
        }
        return builder.toString().trim();
    }
}

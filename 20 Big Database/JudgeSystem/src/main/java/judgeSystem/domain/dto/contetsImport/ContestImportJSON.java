package judgeSystem.domain.dto.contetsImport;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ContestImportJSON {
    @Expose
    private String name;

    @Expose
    private ContestCategoryImportJSON category;

    @Expose
    private List<ContestStrategyImportJSON> allowedStrategies;

    public ContestImportJSON() {
        this.allowedStrategies=new ArrayList<>();
    }

    public ContestCategoryImportJSON getCategory() {
        return category;
    }

    public void setCategory(ContestCategoryImportJSON category) {
        this.category = category;
    }

    public List<ContestStrategyImportJSON> getAllowedStrategies() {
        return allowedStrategies;
    }

    public void setAllowedStrategies(List<ContestStrategyImportJSON> allowedStrategies) {
        this.allowedStrategies = allowedStrategies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

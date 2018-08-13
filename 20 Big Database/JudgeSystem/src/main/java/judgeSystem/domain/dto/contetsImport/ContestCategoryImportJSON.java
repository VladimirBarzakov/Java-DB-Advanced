package judgeSystem.domain.dto.contetsImport;

import com.google.gson.annotations.Expose;

public class ContestCategoryImportJSON {

    @Expose
    private String name;

    public ContestCategoryImportJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package judgeSystem.domain.dto.contetsImport;

import com.google.gson.annotations.Expose;

public class ContestStrategyImportJSON {

    @Expose
    private String name;

    public ContestStrategyImportJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

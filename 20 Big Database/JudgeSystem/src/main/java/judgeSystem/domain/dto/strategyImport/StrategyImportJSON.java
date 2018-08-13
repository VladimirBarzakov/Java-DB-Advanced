package judgeSystem.domain.dto.strategyImport;

import com.google.gson.annotations.Expose;

public class StrategyImportJSON {


    private long id;

    @Expose
    private String name;

    public StrategyImportJSON() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

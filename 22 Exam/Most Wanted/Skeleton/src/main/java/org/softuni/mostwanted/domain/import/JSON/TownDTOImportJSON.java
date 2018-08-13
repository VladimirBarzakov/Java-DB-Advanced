package org.softuni.mostwanted.domain.JSON.JSON;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownDTOImportJSON {

    @Expose
    @NotNull
    private String name;

    public TownDTOImportJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

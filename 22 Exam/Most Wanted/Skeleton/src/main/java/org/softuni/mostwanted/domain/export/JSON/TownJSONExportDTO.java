package org.softuni.mostwanted.domain.export.JSON;

import com.google.gson.annotations.Expose;

public class TownJSONExportDTO {

    @Expose
    private String name;

    @Expose
    private Long racers;

    public TownJSONExportDTO() {
    }

    public TownJSONExportDTO(String name, Long racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRacers() {
        return racers;
    }

    public void setRacers(Long racers) {
        this.racers = racers;
    }
}

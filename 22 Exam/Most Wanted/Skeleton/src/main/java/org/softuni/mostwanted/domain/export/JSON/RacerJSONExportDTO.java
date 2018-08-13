package org.softuni.mostwanted.domain.export.JSON;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class RacerJSONExportDTO {

    @Expose
    private String name;

    @Expose
    private Integer age;

    @Expose
    private List<String> cars;

    public RacerJSONExportDTO() {
        this.cars=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }
}

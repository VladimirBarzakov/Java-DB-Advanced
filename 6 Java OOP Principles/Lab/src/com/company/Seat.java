package com.company;

import java.io.Serializable;

public class Seat implements Car, Serializable {
    private String model;
    private String color;
    private int horsePower;
    private String countryProduced;


    public Seat(String model, String color, int horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getColor() {
        return color;
    }
    @Override
    public int getHorsePower() {
        return horsePower;
    }

    public String getCountryProduced() {
        return countryProduced;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires"+System.lineSeparator(),this.model,this.countryProduced,TIRES);
    }
}

package com.company;

public class Ferrari implements Car{
    private String model = "488-Spider";
    private String driver;

    public Ferrari( String driver) {
        this.driver = driver;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void brakes() {
        System.out.print("Brakes!");
    }

    @Override
    public void gasPedal() {
        System.out.print("Zadu6avam sA!");
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s"+System.lineSeparator(),getModel(),"Brakes!","Zadu6avam sA!",getDriver());
    }
}

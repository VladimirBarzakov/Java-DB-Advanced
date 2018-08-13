package com.company;

import java.text.DecimalFormat;

public class Car implements Drivable {
    private double fuelQuantity;
    private double fuelConsumption;
    private double distanceTravelled=0;

    public Car(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = (fuelConsumption+0.9);
    }

    @Override
    public void refuel(double fuel) {
        this.fuelQuantity+=fuel;
    }

    @Override
    public void drive(double distance) {
        if (distance*this.fuelConsumption<=this.fuelQuantity){
            this.fuelQuantity-=distance*this.fuelConsumption;
            this.distanceTravelled+=distance;
            DecimalFormat format = new DecimalFormat("0.###############");
            System.out.printf("Car travelled %s km"+System.lineSeparator(),format.format(distance));
        }
        else{
            System.out.println("Car needs refueling");
        }
    }

    @Override
    public double getFuelQuantity() {
        return fuelQuantity;
    }

    @Override
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public double getDistanceTravelled() {
        return distanceTravelled;
    }
    @Override
    public String toString(){
        return String.format("Car: %.2f",this.fuelQuantity);
    }
}

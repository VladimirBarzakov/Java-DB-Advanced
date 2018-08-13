package com.company;

import java.text.DecimalFormat;

public class Truck implements Drivable {
    private double fuelQuantity;
    private double fuelConsumption;
    private double distanceTravelled=0;

    public Truck(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption+1.6;
    }

    @Override
    public void refuel(double fuel) {
        this.fuelQuantity+=fuel*0.95;
    }

    @Override
    public void drive(double distance) {
        if (distance*this.fuelConsumption<=this.fuelQuantity){
            this.fuelQuantity-=distance*this.fuelConsumption;
            this.distanceTravelled+=distance;
            DecimalFormat format = new DecimalFormat("0.###############");
            System.out.printf("Truck travelled %s km"+System.lineSeparator(),format.format(distance));
        }
        else{
            System.out.println("Truck needs refueling");
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
        return String.format("Truck: %.2f",this.fuelQuantity);
    }
}

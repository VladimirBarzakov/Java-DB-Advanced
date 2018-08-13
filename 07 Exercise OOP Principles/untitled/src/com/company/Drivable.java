package com.company;

public interface Drivable {
    public double fuelQuantity=0;
    public double fuelConsumption=0;
    public double distanceTravelled=0;

    public double getFuelQuantity();
    public double getFuelConsumption();
    public double getDistanceTravelled();

    public void refuel(double fuel);
    public void drive(double distance);

}

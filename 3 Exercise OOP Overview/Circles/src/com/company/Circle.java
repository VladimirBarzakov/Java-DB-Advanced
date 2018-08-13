package com.company;

public class Circle {

    private Point center;

    private double radius;

    public Circle(){}

    public Circle(double x, double y, double r){
        this.center=new Point(x,y);
        this.radius=r;
    }

    public boolean intersect(Circle other){
        double distance = this.center.getDistance(other.center);
        return distance <= this.radius + other.radius;

    }



    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

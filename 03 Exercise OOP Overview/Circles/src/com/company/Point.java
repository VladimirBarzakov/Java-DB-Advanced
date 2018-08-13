package com.company;

public class Point {
    public double X;
    public double Y;

    public Point(){}

    public double getDistance(Point other){
        double distance = Math.sqrt(Math.pow((this.X-other.X),2)+Math.pow((this.Y-other.Y),2));
        return distance;
    }

    public Point(double x, double y){
        this.X=x;
        this.Y=y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}

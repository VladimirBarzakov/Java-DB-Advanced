package com.company;

public class Rectangle implements Drawable {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.height; i++) {
            System.out.print("*");
            for (int j = 0; j < this.width-1; j++) {
                if (i==0||i==this.height-1){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.print("*");
            System.out.print("\n");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

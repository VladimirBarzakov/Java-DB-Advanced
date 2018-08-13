package com.company;

public class  Robot extends Minion {
    private String mode;
    private String id;

    public Robot(String mode, String id) {
        this.mode = mode;
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public String getId() {
        return id;
    }


    public boolean isIdFake(String fragment) {
        return this.id.endsWith(fragment);
    }
}

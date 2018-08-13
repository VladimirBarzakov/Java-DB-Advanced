package com.company;

public abstract class Animal {
    private String name;
    private Long age;
    private String gender;

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Animal(String name, Long age, String gender) {
        if (name.equals("")||age<=0||(!gender.equals("Male")&&!gender.equals("Female"))){
            throw new MyException("Invalid input!");
        }
        this.name = name;
        this.age = age;
        this.gender = gender;
    }



    public void produceSound(){
        System.out.println("Not implemented!");
    };
}

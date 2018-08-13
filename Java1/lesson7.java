package com.company;

public class lesson7 {
    public static void main(String[] args) {
        Cat[] cats = {new Cat("VASO", 300),new Cat("VASO", 200) ,new Cat("VASO", 100) };
        Plate plate = new Plate(500);
        for (Cat c : cats) {
            c.eat(plate);
            System.out.println(c);
        }
        System.out.println(plate);
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean isFull;
    Cat(String name, int appetite){
        this.name = name;
        this.appetite = appetite;
        this.isFull = false;
    }
    void eat(Plate plate){
        this.isFull = plate.decrease(appetite);
        }

    @Override
    public String toString() {
        return name + " , сытость: " + isFull;
    }
}

class Plate {
    private int food;
    Plate(int food){
        this.food = food;
    }
    boolean decrease(int food){
        if (food >this.food){
            System.out.println("В тарелке мало еды, пополните тарелку");
            return false;
        }
        this.food -= food;
        return true;
    }
     void addFood(int food){
        this.food += food;
     }

    @Override
    public String toString() {
        return "Food: " + food;
    }
}
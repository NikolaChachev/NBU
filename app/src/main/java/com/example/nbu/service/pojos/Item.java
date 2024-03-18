package com.example.nbu.service.pojos;

public class Item implements ITradable {
    private final String name;
    private final int value;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public double sellPrice() {
        double percentage = value / 10.d * 7;
        return (int)percentage;
    }

    @Override
    public double buyPrice() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

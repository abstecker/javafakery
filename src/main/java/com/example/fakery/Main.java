package com.example.fakery;

import net.datafaker.Faker;

public class Main {
    @FakeIEN
    private String ien1;

    @FakeIEN
    private String ien2;

    public static void main(String[] args) {
        Main main = new Main();
        Faker faker = new Faker();
        IENFieldPopulator populator = new IENFieldPopulator(faker);
        populator.populate(main);
        System.out.println("Generated IEN #1: " + main.ien1);
        System.out.println("Generated IEN #2: " + main.ien2);
    }
}
package com.ks.adventure.shared;

import com.github.javafaker.Faker;

public class MotherCreator {
    private final static Faker faker = new Faker();

    public static Faker random() {
        return faker;
    }
}

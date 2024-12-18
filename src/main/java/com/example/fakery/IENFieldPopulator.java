package com.example.fakery;

import net.datafaker.Faker;

import java.lang.reflect.Field;

public class IENFieldPopulator {

    private final Faker faker;

    public IENFieldPopulator(Faker faker) {
        this.faker = faker;
    }

    public void populate(Object object) {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FakeIEN.class)) {
                field.setAccessible(true);
                try {
                    IENProvider ienProvider = new IENProvider(faker);
                    field.set(object, ienProvider.ien());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
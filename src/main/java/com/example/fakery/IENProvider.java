package com.example.fakery;

import net.datafaker.Faker;
import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;

public class IENProvider extends AbstractProvider<BaseProviders> {

    public IENProvider(final Faker faker) {
        super(faker);
    }

    public String ien() {
        return faker.regexify("\\d{2}-\\d{7}");
    }
}

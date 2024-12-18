package com.example.fakery;

import net.datafaker.Faker;
import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.service.RandomService;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.example.fakery.dto.IEN;

import java.nio.file.Paths;
import java.util.Locale;
import java.util.Random;

import static net.datafaker.transformations.Field.field;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IENFakerTest {

    public static class IENCustomFaker extends BaseFaker {
        public IENProviderFromFile getIENProvider() {
            return getProvider(IENProviderFromFile.class, IENProviderFromFile::new);
        }
    }

    public static class IENProviderFromFile extends AbstractProvider<BaseProviders> {
        private static final String KEY = "iensfromfile";

        protected IENProviderFromFile(BaseProviders faker) {
            super(faker);
            faker.addPath(Locale.ENGLISH, Paths.get("src/test/resources/iens.yml"));
        }

        public String ien() {
            return resolve(KEY + ".iens");
        }
    }

    @RepeatedTest(2)
    void nieTestExpressionFromFile() {
        IENCustomFaker ienFaker = new IENCustomFaker();

        assertThat(ienFaker.getIENProvider().ien()).matches("\\d{2}-\\d{7}");
    }

    @Test
    public void shouldGenerateEntityWithDefaultSchema() {
        var ien = IENCustomFaker.populate(IEN.class);

        assertNotNull(ien);
        assertThat(ien.getIen()).matches("\\d{2}-\\d{7}");
    }

    public static Schema<Object, ?> defaultSchema() {
        IENCustomFaker ienFaker = new IENCustomFaker();
        return Schema.of(field("ien", () -> ienFaker.getIENProvider().ien()));
    }
}

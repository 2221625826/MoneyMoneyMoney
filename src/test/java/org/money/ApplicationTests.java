package org.money;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Value("${spring.profiles.active}")
    private String env;

    @Test
    public void envTest() {
        assert (Objects.equals(env, "dev"));
        System.out.printf(env);
    }
}

package br.com.dbserver.desafio.testeconteiners;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;
@ActiveProfiles("test")
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static PostgreSQLContainer<?> potgresql = new PostgreSQLContainer<>("postgres:16.2");

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource("testcontainers",
                    createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }

        private Map<String, Object> createConnectionConfiguration() {
            return Map.of("spring.datasource.url", potgresql.getJdbcUrl(),
                          "spring.datasource.username", potgresql.getUsername(),
                          "spring.datasource.password", potgresql.getPassword()
            );
        }

        private static void startContainers() {
            Startables.deepStart(Stream.of(potgresql)).join();
        }
    }
}

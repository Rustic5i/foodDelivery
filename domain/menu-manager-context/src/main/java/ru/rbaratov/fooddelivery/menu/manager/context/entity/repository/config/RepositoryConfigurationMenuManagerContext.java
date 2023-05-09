package ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.menu.manager.context")
public class RepositoryConfigurationMenuManagerContext {
}

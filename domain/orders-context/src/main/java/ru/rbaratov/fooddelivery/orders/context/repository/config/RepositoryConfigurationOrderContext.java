package ru.rbaratov.fooddelivery.orders.context.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.orders.context.repository")
public class RepositoryConfigurationOrderContext {
}

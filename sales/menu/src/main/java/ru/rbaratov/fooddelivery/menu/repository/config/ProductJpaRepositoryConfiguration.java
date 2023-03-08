package ru.rbaratov.fooddelivery.menu.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.menu.repository")
public class ProductJpaRepositoryConfiguration {
}

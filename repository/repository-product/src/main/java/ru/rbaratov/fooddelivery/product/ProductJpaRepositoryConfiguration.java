package ru.rbaratov.fooddelivery.product;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.product.repository")
public class ProductJpaRepositoryConfiguration {
}

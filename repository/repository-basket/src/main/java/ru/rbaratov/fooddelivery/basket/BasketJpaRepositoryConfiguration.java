package ru.rbaratov.fooddelivery.basket;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.basket.repository")
public class BasketJpaRepositoryConfiguration {
}


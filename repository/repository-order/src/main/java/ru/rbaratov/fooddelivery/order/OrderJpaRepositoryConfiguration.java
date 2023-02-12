package ru.rbaratov.fooddelivery.order;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.order.repository")
public class OrderJpaRepositoryConfiguration {
}

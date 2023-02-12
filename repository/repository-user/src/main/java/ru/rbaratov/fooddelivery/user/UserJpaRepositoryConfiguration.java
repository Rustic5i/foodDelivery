package ru.rbaratov.fooddelivery.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.rbaratov.fooddelivery.user.repository")
public class UserJpaRepositoryConfiguration {
}

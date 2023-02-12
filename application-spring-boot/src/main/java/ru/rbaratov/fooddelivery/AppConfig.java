package ru.rbaratov.fooddelivery;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(value = "ru.rbaratov.fooddelivery")
public class AppConfig {
}

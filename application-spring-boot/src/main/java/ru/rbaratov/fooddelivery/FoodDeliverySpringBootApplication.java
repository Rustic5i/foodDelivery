package ru.rbaratov.fooddelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.rbaratov"})
public class FoodDeliverySpringBootApplication {

    public static void main(String... args) {
        SpringApplication.run(FoodDeliverySpringBootApplication.class, args);
    }

}

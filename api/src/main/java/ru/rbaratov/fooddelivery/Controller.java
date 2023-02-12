package ru.rbaratov.fooddelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rbaratov.fooddelivery.services.basket.BasketService;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private BasketService basketService;

    @GetMapping
    public String delete(){
        basketService.addProduct(UUID.randomUUID());
        return "test";
    }
}

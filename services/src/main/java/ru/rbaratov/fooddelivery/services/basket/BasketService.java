package ru.rbaratov.fooddelivery.services.basket;

import java.util.UUID;

public interface BasketService {

    void addProduct(UUID productId);

    void deleteProduct(UUID productId);
}

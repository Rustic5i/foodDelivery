package ru.rbaratov.fooddelivery.orders.context.domain.repository.item;

import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.util.Optional;

public interface BuyerCartAggregateRepository {

    Optional<Cart> findByBuyerPhoneNumber(PhoneNumber phoneNumber);

    void save(Cart cart);
}

package ru.rbaratov.fooddelivery.orders.context.domain.repository.item;

import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.util.Optional;

public interface BuyerCartAggregateRepository {

    Optional<BuyerCart> findByBuyerPhoneNumber(PhoneNumber phoneNumber);

    void save(BuyerCart buyerCart);
}

package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<BuyerCart, UUID> {

    boolean existsByBuyer(Buyer buyer);

    Optional<BuyerCart> findByBuyerPhoneNumber(PhoneNumber phoneNumberBuyer);
}

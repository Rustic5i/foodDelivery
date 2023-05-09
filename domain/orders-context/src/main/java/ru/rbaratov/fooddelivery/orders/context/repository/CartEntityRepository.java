package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerCartEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartEntityRepository extends JpaRepository<BuyerCartEntity, UUID> {

    Optional<BuyerCartEntity> findByBuyerPhoneNumber(String phoneNumberBuyer);
}

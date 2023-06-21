package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerEntityRepository extends JpaRepository<BuyerEntity, UUID> {
    Optional<BuyerEntity> findByPhoneNumber(String phoneNumber);
}

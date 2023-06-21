package ru.rbaratov.fooddelivery.orders.context.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartEntityRepository extends JpaRepository<CartEntity, UUID> {

    /**
     * Найти корзину товаров по номеру телефона покупателя
     *
     * @param phoneNumberBuyer номер телефона покупателя
     * @return корзина покупателя
     */
    @Query("SELECT c FROM CartEntity c where c.buyer.phoneNumber = ?1")
    Optional<CartEntity> findByBuyerPhoneNumber(String phoneNumberBuyer);
}

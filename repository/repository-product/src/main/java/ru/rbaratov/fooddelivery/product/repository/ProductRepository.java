package ru.rbaratov.fooddelivery.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.domain.product.Product;

import java.util.UUID;

/**
 * Доступ к данным товара {@link Product}
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}

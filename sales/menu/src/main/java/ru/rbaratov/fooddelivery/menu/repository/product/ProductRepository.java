package ru.rbaratov.fooddelivery.menu.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductName;

import java.util.UUID;

/**
 * Доступ к данным товара {@link Product}
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Есть ли товар с данным именем
     * @param productName имя товара
     * @return true если данный товар с таким именем уже есть
     */
    Boolean existsByName(ProductName productName);
}

package ru.rbaratov.fooddelivery.menu.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.menu.domain.ProductCategory;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.CategoryName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, UUID> {

    Boolean existsByName(CategoryName categoryName);

    Optional<ProductCategory> findByName(CategoryName categoryName);
}

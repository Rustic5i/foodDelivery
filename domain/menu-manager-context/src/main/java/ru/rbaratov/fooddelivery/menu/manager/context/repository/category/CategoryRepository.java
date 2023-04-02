package ru.rbaratov.fooddelivery.menu.manager.context.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.ItemCategory;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, UUID> {

    Boolean existsByName(CategoryName categoryName);

    Optional<ItemCategory> findByName(CategoryName categoryName);
}

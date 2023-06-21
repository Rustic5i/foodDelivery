package ru.rbaratov.fooddelivery.menu.manager.context.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.CategoryEntity;

import java.util.UUID;

@Repository
public interface ItemCategoryEntityRepository extends JpaRepository<CategoryEntity, UUID> {
    CategoryEntity findByName(String name);
}

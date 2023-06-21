package ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {

    Boolean existsMenuByName(String menuName);

    Optional<MenuEntity> findByName(String menuName);

    MenuEntity getById(UUID id);
}

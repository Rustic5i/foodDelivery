package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;

import java.util.Optional;
import java.util.UUID;

public interface MenuAggregateRepository {

    Optional<Menu> getById(UUID id);

    Optional<Menu> findByName(MenuName menuName);

    void save(Menu menu);

    Boolean existsMenuByName(MenuName menuName);
}

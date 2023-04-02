package ru.rbaratov.fooddelivery.menu.manager.context.repository.menu;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    Boolean existsMenuByName(MenuName menuName);

    Optional<Menu> findByName(MenuName menuName);
}

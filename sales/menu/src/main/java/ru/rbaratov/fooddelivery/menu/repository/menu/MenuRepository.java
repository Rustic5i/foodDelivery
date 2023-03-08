package ru.rbaratov.fooddelivery.menu.repository.menu;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    Boolean existsMenuByName(MenuName menuName);

    Optional<Menu> findByName(MenuName menuName);
}

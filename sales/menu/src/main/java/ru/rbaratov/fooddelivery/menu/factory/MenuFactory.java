package ru.rbaratov.fooddelivery.menu.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;
import ru.rbaratov.fooddelivery.menu.repository.menu.MenuRepository;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Фабрика для создания меню
 */
@Component
public class MenuFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuFactory.class);

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Создать новое меню
     *
     * @param menuName названия меню
     * @return новое меню
     */
    public Menu createNewMenu(@NonNull MenuName menuName) {
        Objects.requireNonNull(menuName, "Нельзя создать меню без названия");
        Boolean existsMenuByName = menuRepository.existsMenuByName(menuName);
        if (existsMenuByName) {
            throw new RuntimeException(MessageFormat.format("Меню с {0} именем уже существует", menuName.value()));
        }
        Menu newMenu = new Menu(menuName);
        LOGGER.info("Создано меню с именем {}", menuName.value());
        return newMenu;
    }
}

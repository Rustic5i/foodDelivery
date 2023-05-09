package ru.rbaratov.fooddelivery.menu.manager.context.domain.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.repository.MenuAggregateRepository;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Фабрика для создания меню
 */
@Component
public class MenuFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuFactory.class);

    @Autowired
    private MenuAggregateRepository aggregateRepository;

    /**
     * Создать новое меню
     *
     * @param menuName названия меню
     * @return новое меню
     */
    public Menu createNewMenu(@NonNull MenuName menuName) {
        Objects.requireNonNull(menuName, "Нельзя создать меню без названия");
        Boolean existsMenuByName = aggregateRepository.existsMenuByName(menuName);
        if (existsMenuByName) {
            throw new RuntimeException(MessageFormat.format("Меню с именем \"{0}\" уже существует", menuName.value()));
        }
        Menu menu = new Menu(menuName);
        LOGGER.info("Создано меню с именем {}", menuName.value());
        return menu;
    }
}

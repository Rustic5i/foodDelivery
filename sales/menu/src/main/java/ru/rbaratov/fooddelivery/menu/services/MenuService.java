package ru.rbaratov.fooddelivery.menu.services;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;

/**
 * Сервис отвечает за инфраструктурную логику меню
 */
public interface MenuService {

    /**
     * Создать меню
     *
     * @param menuName названия меню
     */
    void createNewMenu(@NonNull MenuName menuName);

    /**
     * Добавить товар в меню
     *
     * @param product  товар
     * @param menuName названия меню
     */
    void addProductInMenu(Product product, MenuName menuName);

    /**
     * Удалить продукт из меню
     *
     * @param product  товар
     * @param menuName названия меню
     */
    void removeProductFromMenu(Product product, MenuName menuName);

    /**
     * Показать меню
     *
     * @param menuName названия меню
     * @return меню
     */
    Menu giveMenu(MenuName menuName);
}

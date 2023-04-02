package ru.rbaratov.fooddelivery.menu.manager.context.services;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;

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

//    /**
//     * Добавить товар в меню
//     *
//     * @param item  товар
//     * @param menuName названия меню
//     */
//    void addItemInMenu(Item item, MenuName menuName);

//    /**
//     * Удалить продукт из меню
//     *
//     * @param item  товар
//     * @param menuName названия меню
//     */
//    void removeItemFromMenu(Item item, MenuName menuName);

//    /**
//     * Показать меню
//     *
//     * @param menuName названия меню
//     * @return меню
//     */
//    Menu giveMenu(MenuName menuName);
}

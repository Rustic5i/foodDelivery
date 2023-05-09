package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.handlers;

import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.GetMenuByIdQuery;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.MenuDTO;

/**
 * Обработчик запросов.
 */
public interface QueryHandler {

    /**
     * Получить меню по имени.
     *
     * @param query запрос на получения информации о меню
     * @return информация о меню
     */
    MenuDTO getMenuByName(GetMenuByIdQuery query);
}

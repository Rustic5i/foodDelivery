package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;

public interface MenuAggregateRepository {

    Menu getMenu();

    void save(Menu menu);
}

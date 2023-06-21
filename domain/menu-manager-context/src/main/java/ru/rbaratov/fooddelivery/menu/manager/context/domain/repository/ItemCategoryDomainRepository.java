package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;

public interface ItemCategoryDomainRepository {

    void save(Category category);
}

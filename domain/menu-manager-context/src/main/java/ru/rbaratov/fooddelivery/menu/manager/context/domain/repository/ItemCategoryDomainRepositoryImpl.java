package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.CategoryEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.ItemCategoryEntityRepository;

@Component
public class ItemCategoryDomainRepositoryImpl implements ItemCategoryDomainRepository {

    @Autowired
    private ItemCategoryEntityRepository itemCategoryEntityRepository;


    @Override
    public void save(Category category) {
        itemCategoryEntityRepository.save(new CategoryEntity(category.getName()));
    }
}

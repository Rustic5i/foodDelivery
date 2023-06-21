package ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.CategoryEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.ItemEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.ItemCategoryEntityRepository;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu.MenuRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class MenuM {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ItemCategoryEntityRepository itemCategoryEntityRepository;

    public Menu toMenu(MenuEntity entity) {
        Set<Item> items = new HashSet<>();
        if (CollectionUtils.isNotEmpty(entity.getItems())) {
            for (ItemEntity itemEntity : entity.getItems()) {
                items.add(toItem(itemEntity));
            }
        }
        return Menu.startRevival(entity.getId(), entity.getName())
                .requiredItems(items)
                .revive();
    }

    public Item toItem(ItemEntity entity) {
        return Item.startRevival()
                .requiredId(entity.getId())
                .requiredName(new ItemName(entity.getName()))
                .requiredPrice(new Money(entity.getPrice()))
                .requiredOldPrice(entity.getOldPrice() == null ? null : new Money(entity.getOldPrice()))
                .requiredItemDescription(new ItemDescription(entity.getDescription()))
                .requiredItemSize(new ItemSize(entity.getSize(), entity.getSizeUnit()))
                .requiredCategory(new Category(entity.getCategory().getName()))
                .setIsNewItem(entity.isNewItem())
                .revive();
    }

    public MenuEntity toMenuEntity(Menu menu) {
        MenuEntity menuEntity = menuRepository.getById(menu.getId());
        Set<ItemEntity> itemEntities = new HashSet<>();
        menuEntity.setName(menu.getName());
        for (Item item : menu.getItems()) {
            itemEntities.add(toItemEntity(item, menuEntity));
        }
        menuEntity.setItems(itemEntities);
        return menuEntity;
    }

    private ItemEntity toItemEntity(Item item, MenuEntity menuEntity) {
        ItemEntity entity = null;
        if (item.getId() != null) {
            entity = menuEntity.getItems().stream().filter(i -> i.getId() == item.getId()).findFirst().get();
        } else {
            entity = new ItemEntity();
        }
        entity.setDescription(item.getDescription().value());
        entity.setName(item.getName().value());
        entity.setNewItem(item.isNewItem());
        entity.setOldPrice(item.getOldPrice() == null ? null : item.getOldPrice().value());
        entity.setPrice(item.getPrice().value());
        entity.setSet(item.isSet());
        entity.setSize(item.getSize().getSize());
        entity.setSizeUnit(item.getSize().getSizeUnit());
        if (item.getCategory() != null) {
            CategoryEntity categoryEntity = itemCategoryEntityRepository.findByName(item.getCategory().getName());
            entity.setCategory(categoryEntity);
        }
        return entity;
    }
}

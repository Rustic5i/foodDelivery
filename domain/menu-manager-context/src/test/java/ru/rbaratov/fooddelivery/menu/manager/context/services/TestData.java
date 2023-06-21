//package ru.rbaratov.fooddelivery.menu.manager.context.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import ru.rbaratov.fooddelivery.common.valueobject.SizeUnit;
//import ru.rbaratov.fooddelivery.common.valueobject.item.CategoryName;
//import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.ItemEntity;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.CategoryEntity;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
//import ru.rbaratov.fooddelivery.menu.manager.context.dto.ItemDTO;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.factory.ItemFactory;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.factory.MenuFactory;
//import ru.rbaratov.fooddelivery.menu.manager.context.repository.category.CategoryRepository;
//import ru.rbaratov.fooddelivery.menu.manager.context.repository.item.ItemRepository;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu.MenuRepository;
//
//import java.util.Optional;
//
///**
// * Подготовка тестовых данных в базе данных
// */
//public abstract class TestData {
//
//    @Autowired
//    protected MenuRepository menuRepository;
//    @Autowired
//    protected MenuFactory menuFactory;
//    @Autowired
//    protected ItemFactory itemFactory;
//    @Autowired
//    protected ItemRepository itemRepository;
//    @Autowired
//    protected CategoryRepository categoryRepository;
//
//    protected ItemEntity createAndSaveItem(@NonNull final String itemName, @NonNull final String categoryName) {
//        CategoryEntity itemCategory = createAndSaveItemCategory(categoryName);
//        ItemDTO itemDTO = getItemDTO(itemName, itemCategory.nameCategory());
//        ItemEntity item = itemFactory.createNewItem(itemDTO);
//        return itemRepository.save(item);
//    }
//
//    protected CategoryEntity createAndSaveItemCategory(final String categoryName) {
//        CategoryName newCategoryName = new CategoryName(categoryName);
//        Optional<CategoryEntity> itemCategory = categoryRepository.findByName(newCategoryName);
//        if (itemCategory.isEmpty()) {
//            return categoryRepository.save(new CategoryEntity(newCategoryName));
//        }
//        return itemCategory.get();
//    }
//
//    protected MenuEntity createAndSaveMenu(final String menuName) {
//        MenuEntity menu = menuFactory.createNewMenu(new MenuName(menuName));
//        return menuRepository.save(menu);
//    }
//
//    private ItemDTO getItemDTO(@NonNull final String itemName, @NonNull CategoryName categoryName) {
//        ItemDTO itemDTO = new ItemDTO();
//        itemDTO.setName(itemName);
//        itemDTO.setCategoryName(categoryName.value());
//        itemDTO.setPrice(450f);
//        itemDTO.setSize(1);
//        itemDTO.setSizeUnit(SizeUnit.PCS);
//        itemDTO.setDescription("Очень вкусная пицца из 4 разных сыров");
//
//        return itemDTO;
//    }
//}

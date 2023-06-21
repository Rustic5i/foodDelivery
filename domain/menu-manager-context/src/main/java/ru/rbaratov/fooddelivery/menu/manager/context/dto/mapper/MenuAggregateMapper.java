//package ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.Category;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.CategoryEntity;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.ItemEntity;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
//
//@Mapper(componentModel = "spring")
//public interface MenuAggregateMapper {
//
//    @Mappings({
//            @Mapping(target = "name", expression = "java(item.getName() != null ? item.getName().value() : null)"),
//            @Mapping(target = "price", expression = "java(item.getPrice() != null ? item.getPrice().value() : null)"),
//            @Mapping(target = "oldPrice", expression = "java(item.getOldPrice() != null ? item.getOldPrice().value() : null)"),
//            @Mapping(target = "description", expression = "java(item.getDescription() != null ? item.getDescription().value() : null)"),
//            @Mapping(target = "size", expression = "java(item.getSize() != null ? item.getSize().getSize() : null)"),
//            @Mapping(target = "category", expression = "java(toItemCategoryEntity(item.getCategory()))")
//    })
//    ItemEntity toItemEntity(Item item);
//
//    default CategoryEntity toItemCategoryEntity(Category category) {
//        if (category == null) {
//            return null;
//        }
//        CategoryEntity categoryEntity = new CategoryEntity(category.getName());
//        return categoryEntity;
//    }
//
//    @Mappings({
//            @Mapping(target = "name", expression = "java(menu.getName() != null ? menu.getName().value() : null)")
//    })
//    MenuEntity toMenuEntity(Menu menu);
//
//    @Mappings({
//            @Mapping(target = "name", expression = "java(new ItemName(entity.getName()))"),
//            @Mapping(target = "price", expression = "java(new Money(entity.getPrice()))"),
//            @Mapping(target = "oldPrice", expression = "java(new Money(entity.getOldPrice()))"),
//            @Mapping(target = "description", expression = "java(new ItemDescription(entity.getDescription()))"),
//            @Mapping(target = "size", expression = "java(new ItemSize(entity.getSize(), entity.getSizeUnit()))"),
//            @Mapping(target = "category", expression = "java(itemCategoryEntityToItemCategory(entity.getCategory()))")
//    })
//    Item toItem(ItemEntity entity);
//
//    default Category itemCategoryEntityToItemCategory(CategoryEntity categoryEntity) {
//        if (categoryEntity == null) {
//            return null;
//        }
//        Category category = new Category(categoryEntity.getName());
//        return category;
//    }
//
//    default Menu toMenu(MenuEntity entity) {
//        return Menu.startRevival(entity.getId(), entity.getName())
//                .requiredItems(toItem(entity.getItems()))
//                .revive();
//    }
//}

package ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.ItemDTO;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.MenuDTO;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.ItemEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;

@Mapper(componentModel = "spring")
public interface MenuDTOMapper {

    @Mappings({
            @Mapping(target = "categoryName", expression = "java(entity.getCategory() != null ? entity.getCategory().getName() : null)")
    })
    ItemDTO toItemDTO(ItemEntity entity);

    MenuDTO toMenuDTO(MenuEntity entity);
}

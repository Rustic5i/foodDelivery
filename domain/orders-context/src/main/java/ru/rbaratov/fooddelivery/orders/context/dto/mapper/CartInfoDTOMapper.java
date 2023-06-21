package ru.rbaratov.fooddelivery.orders.context.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.rbaratov.fooddelivery.orders.context.dto.CartInfoDTO;
import ru.rbaratov.fooddelivery.orders.context.dto.CartItemDTO;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.CartItemEntity;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartInfoDTOMapper {

    @Mappings({
            @Mapping(target = "selectItems", expression = "java(toCartItemDTO(entity.getCartItemEntity()))")
    })
    CartInfoDTO toItemDTO(CartEntity entity);

    List<CartItemDTO> toCartItemDTO(Set<CartItemEntity> entities);
}

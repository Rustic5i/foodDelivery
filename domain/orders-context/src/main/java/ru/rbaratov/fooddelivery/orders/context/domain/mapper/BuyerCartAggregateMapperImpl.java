package ru.rbaratov.fooddelivery.orders.context.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.domain.Item;
import ru.rbaratov.fooddelivery.orders.context.domain.SelectItemInCart;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Quantity;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerCartEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.SelectItemInCartEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.CartEntityRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class BuyerCartAggregateMapperImpl implements BuyerCartAggregateMapper {

    @Autowired
    private CartEntityRepository cartEntityRepository;


    @Override
    public BuyerCart toBuyerCart(BuyerCartEntity entity) {
        Buyer buyer = new Buyer(
                new PhoneNumber(entity.getBuyer().getPhoneNumber()),
                entity.getBuyer().getFirstName(),
                entity.getBuyer().isActive()
        );
        Set<SelectItemInCart> items = toDomain(entity.getSelectItems());
        BuyerCart aggregate = new BuyerCart(buyer, items);
        return aggregate;
    }

    private Set<SelectItemInCart> toDomain(Set<SelectItemInCartEntity> selectItemsEntity) {
        if (selectItemsEntity == null) {
            return new HashSet<>();
        }
        Set<SelectItemInCart> selectItems = new HashSet<>();
        selectItemsEntity.forEach(s -> {
            Item item = new Item(new ItemName(s.getItem().getName()), new Money(s.getTotalPrice()));
            SelectItemInCart selectItemInCart = new SelectItemInCart(item, new Quantity(s.getQuantity()));
            selectItems.add(selectItemInCart);
        });
        return selectItems;
    }

    @Override
    public BuyerCartEntity toBuyerCartEntity(BuyerCart аggregate) {
        BuyerCartEntity entity = cartEntityRepository.findById(аggregate.getId()).orElseThrow();
        entity.setSelectItems(toEntity(аggregate.showSelectItemInCart()));
        entity.setTotalPrice(аggregate.showTotalPrice().value());
        return entity;
    }

    public Set<SelectItemInCartEntity> toEntity(Collection<SelectItemInCart> selectItem) {
        Set<SelectItemInCartEntity> selectItemEntities = new HashSet<>();
        selectItem.forEach(s -> {
            SelectItemInCartEntity selectItemInCartEntity = new SelectItemInCartEntity();
            selectItemInCartEntity.setIdItem(s.showIdSelectItem());
            selectItemInCartEntity.setQuantity(s.showQuantity());
            selectItemInCartEntity.setTotalPrice(s.showTotalPrice());
            selectItemEntities.add(selectItemInCartEntity);
        });
        return selectItemEntities;
    }
}

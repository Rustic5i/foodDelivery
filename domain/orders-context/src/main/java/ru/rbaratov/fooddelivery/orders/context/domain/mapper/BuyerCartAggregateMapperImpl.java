package ru.rbaratov.fooddelivery.orders.context.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.domain.Item;
import ru.rbaratov.fooddelivery.orders.context.domain.ItemInCart;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.Quantity;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.CartItemEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.ItemEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.CartEntityRepository;
import ru.rbaratov.fooddelivery.orders.context.repository.ItemEntityRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BuyerCartAggregateMapperImpl implements BuyerCartAggregateMapper {

    @Autowired
    private CartEntityRepository cartEntityRepository;
    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Override
    public Buyer to(BuyerEntity entity) {
        return Buyer.startRevival()
                .requiredId(entity.getId())
                .requiredPhoneNumber(new PhoneNumber(entity.getPhoneNumber()))
                .requiredFirstName(entity.getFirstName())
                .requiredIsActive(entity.isActive())
                .revive();
    }

    @Override
    public Cart toBuyerCart(CartEntity entity) {
        Buyer buyer = to(entity.getBuyer());
        Set<ItemInCart> items = toDomain(entity.getCartItemEntity());
        Cart aggregate = Cart.revival(entity.getId(), buyer, items);
        return aggregate;
    }

    private Set<ItemInCart> toDomain(Set<CartItemEntity> selectItemsEntity) {
        if (selectItemsEntity == null) {
            return new HashSet<>();
        }
        Set<ItemInCart> selectItems = new HashSet<>();
        for (CartItemEntity s : selectItemsEntity) {
            Item item = toItem(s.getItem());
            ItemInCart itemInCart = ItemInCart
                    .startRevival()
                    .requiredValues(item, new Quantity(s.getQuantity()), s.getId())
                    .revive();
            selectItems.add(itemInCart);
        }
        return selectItems;
    }

    private Item toItem(ItemEntity item) {
        return Item.startRevival()
                .requiredId(item.getId())
                .requiredName(new ItemName(item.getName()))
                .requiredPrice(new Money(item.getPrice()))
                .revive();
    }

    @Override
    public CartEntity toBuyerCartEntity(Cart аggregate) {
        CartEntity entity = cartEntityRepository.findById(аggregate.getId()).orElseThrow();
        entity.setSelectItems(toEntity(аggregate.showSelectItemInCart(), entity));
        entity.setTotalPrice(аggregate.showTotalPrice().value());
        return entity;
    }

    public Set<CartItemEntity> toEntity(Collection<ItemInCart> selectItem, CartEntity cartEntity) {
        Set<CartItemEntity> cartItemEntities = cartEntity.getCartItemEntity();
        for (ItemInCart itemInCart : selectItem) {
            CartItemEntity cartItemEntity = null;
            if (itemInCart.getId() == null) {
                cartItemEntity = new CartItemEntity();
                cartItemEntity.setBuyerCart(cartEntity);
                Optional<ItemEntity> byId = itemEntityRepository.findById(itemInCart.showIdSelectItem());
                cartItemEntity.setIdItem(byId.get().getId());
            } else {
                cartItemEntity = cartItemEntities.stream()
                        .filter(c -> c.getId() == itemInCart.getId())
                        .findFirst()
                        .orElse(new CartItemEntity());
            }
            cartItemEntity.setQuantity(itemInCart.showQuantity());
            cartItemEntity.setTotalPrice(itemInCart.showTotalPrice());
            cartItemEntities.add(cartItemEntity);
        }
        return cartItemEntities;
    }
}

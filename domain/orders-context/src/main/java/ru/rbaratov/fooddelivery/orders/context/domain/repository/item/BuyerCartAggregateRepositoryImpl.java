package ru.rbaratov.fooddelivery.orders.context.domain.repository.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.domain.mapper.BuyerCartAggregateMapper;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.CartEntityRepository;

import java.util.Optional;

@Component
public class BuyerCartAggregateRepositoryImpl implements BuyerCartAggregateRepository {

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private BuyerCartAggregateMapper mapper;

    @Override
    public Optional<Cart> findByBuyerPhoneNumber(PhoneNumber phoneNumber) {
        Optional<CartEntity> byBuyerPhoneNumber = cartEntityRepository.findByBuyerPhoneNumber(phoneNumber.value());
        if (byBuyerPhoneNumber.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.toBuyerCart(byBuyerPhoneNumber.get()));
    }

    @Override
    public void save(Cart cart) {
        CartEntity entity = mapper.toBuyerCartEntity(cart);
        CartEntity save = cartEntityRepository.save(entity);
    }
}


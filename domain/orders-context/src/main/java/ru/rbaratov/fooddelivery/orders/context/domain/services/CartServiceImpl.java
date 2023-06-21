package ru.rbaratov.fooddelivery.orders.context.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.AuthenticationUserService;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.UserDTO;
import ru.rbaratov.fooddelivery.orders.context.domain.Cart;
import ru.rbaratov.fooddelivery.orders.context.domain.Item;
import ru.rbaratov.fooddelivery.orders.context.domain.repository.cart.ItemRepositoryImpl;
import ru.rbaratov.fooddelivery.orders.context.domain.repository.item.BuyerCartAggregateRepository;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerEntity;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.BuyerEntityRepository;
import ru.rbaratov.fooddelivery.orders.context.repository.CartEntityRepository;

import java.text.MessageFormat;
import java.util.UUID;

@Component
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private BuyerCartAggregateRepository aggregateRepository;
    @Autowired
    private ItemRepositoryImpl itemRepository;
    @Autowired
    private BuyerEntityRepository buyerEntityRepository;
    @Autowired
    private AuthenticationUserService authenticationUserService;
    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Override
    @Transactional
    public void addItemToCart(UUID addItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка добавить в корзину товар не идентифицированным пользователем"));

        Cart cart = aggregateRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        Item item = itemRepository.findById(addItem);
        cart.addItem(item);
        aggregateRepository.save(cart);
        LOGGER.info("Пользователем {} был добавлен товар {} в корзину", currentUser.getPhoneNumber(), item.getName().value());
    }

    @Override
    @Transactional
    public void removeItemFromCart(UUID removeItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка удалить из корзины товар не идентифицированным пользователем"));

        Cart cart = aggregateRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        cart.removeItem(removeItem);
        aggregateRepository.save(cart);
        LOGGER.info("пользователем {} был удален товар {} из корзины", currentUser.getPhoneNumber(), removeItem);
    }

    @Override
    @Transactional
    public CartEntity createCart(PhoneNumber phoneNumber) {//todo временно
        BuyerEntity buyerEntity = buyerEntityRepository.findByPhoneNumber(phoneNumber.value()).get();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setBuyer(buyerEntity);
        cartEntity.setTotalPrice(0f);
        return cartEntityRepository.save(cartEntity);
    }
}

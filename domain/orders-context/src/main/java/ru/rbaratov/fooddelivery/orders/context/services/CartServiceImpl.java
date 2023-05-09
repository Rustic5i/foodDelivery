package ru.rbaratov.fooddelivery.orders.context.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.AuthenticationUserService;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.UserDTO;
import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.domain.Item;
import ru.rbaratov.fooddelivery.orders.context.domain.repository.cart.ItemRepositoryImpl;
import ru.rbaratov.fooddelivery.orders.context.domain.repository.item.BuyerCartAggregateRepository;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;

import java.text.MessageFormat;
import java.util.UUID;

public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private BuyerCartAggregateRepository aggregateRepository;
    @Autowired
    private ItemRepositoryImpl itemRepository;
    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Override
    @Transactional
    public void addItemToCart(UUID addItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка добавить в корзину товар не идентифицированным покупателем"));

        BuyerCart buyerCart = aggregateRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        Item item = itemRepository.findById(addItem);
        buyerCart.addItem(item);
        aggregateRepository.save(buyerCart);
        LOGGER.info("Покупателем {} был добавлен товар {} в корзину", currentUser.getPhoneNumber(), item.getId());
    }

    @Override
    @Transactional
    public void removeItemFromCart(UUID removeItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка удалить из корзины товар не идентифицированным покупателем"));

        BuyerCart buyerCart = aggregateRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        Item item = itemRepository.findById(removeItem);
        buyerCart.removeItem(item);
        aggregateRepository.save(buyerCart);
        LOGGER.info("Покупателем {} был удален товар {} из корзины", currentUser.getPhoneNumber(), item.getId());
    }
}

package ru.rbaratov.fooddelivery.orders.context.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.orders.context.repository.CartRepository;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.AuthenticationUserService;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.UserDTO;
import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.domain.SelectItem;
import ru.rbaratov.fooddelivery.orders.context.repository.SelectItemRepository;

import java.text.MessageFormat;
import java.util.UUID;

public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private SelectItemRepository selectItemRepository;
    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Override
    @Transactional
    public void addItemToCart(UUID addItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка добавить в корзину товар не идентифицированным покупателем"));

        BuyerCart buyerCart = cartRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        SelectItem selectItem = selectItemRepository.findById(addItem).get();
        buyerCart.addItem(selectItem);
        cartRepository.save(buyerCart);
        LOGGER.info("Покупателем {} был добавлен товар {} в корзину", currentUser.getPhoneNumber(), selectItem.getId());
    }

    @Override
    @Transactional
    public void removeItemFromCart(UUID removeItem) {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка удалить из корзины товар не идентифицированным покупателем"));

        BuyerCart buyerCart = cartRepository.findByBuyerPhoneNumber(new PhoneNumber(currentUser.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Корзина покупателя {0}, не найдена", currentUser.getPhoneNumber())));

        SelectItem selectItem = selectItemRepository.findById(removeItem).get();
        buyerCart.removeItem(selectItem);
        cartRepository.save(buyerCart);
        LOGGER.info("Покупателем {} был удален товар {} из корзины", currentUser.getPhoneNumber(), selectItem.getId());
    }
}

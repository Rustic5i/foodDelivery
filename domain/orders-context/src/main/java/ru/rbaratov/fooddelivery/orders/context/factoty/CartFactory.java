package ru.rbaratov.fooddelivery.orders.context.factoty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.domain.BuyerCart;
import ru.rbaratov.fooddelivery.orders.context.repository.CartRepository;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Фабрика по созданию пользовательских корзин
 */
@Component
public class CartFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartFactory.class);

    private CartRepository cartRepository;

    /**
     * Создать новую корзину
     *
     * @param buyer для кого создаем корзину
     * @return новая корзина
     */
    public BuyerCart createNewCart(Buyer buyer) {
        Objects.requireNonNull(buyer);
        boolean isExists = cartRepository.existsByBuyer(buyer);
        if (isExists) {
            throw new RuntimeException(MessageFormat.format("У покупателя {} уже есть корзина, нельзя создать новую", buyer.getPhoneNumber()));
        }
        LOGGER.info("Создано новая корзина для юзера {}", buyer.getPhoneNumber());
        return new BuyerCart(buyer);
    }
}

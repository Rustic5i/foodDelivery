package ru.rbaratov.fooddelivery.orders.context.domain;

import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.Money;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Корзина покупателя
 */
public class BuyerCart extends AbstractDomain {

    /**
     * Пользователь
     */
    private Buyer buyer;

    /**
     * Итоговая цена
     */
    private Money totalPrice = new Money(0);

    /**
     * Список выбранных товаров
     */
    private Set<SelectItemInCart> items = new HashSet<>();

    public BuyerCart(Buyer buyer, Set<SelectItemInCart> items) {
        Objects.requireNonNull(buyer, "Корзина не может быть создана без юзера");
        this.buyer = buyer;
        this.items = items;
        recalculateTotalPrice();
    }

    /**
     * Добавить товар в корзину
     *
     * @param addItem товар, который требуется добавить в корзину
     */
    public void addItem(Item addItem) {
        Optional<SelectItemInCart> cartItem = findItemById(addItem.getId());
        if (cartItem.isEmpty()) {
            SelectItemInCart newSelectItemInCart = new SelectItemInCart(addItem);
            items.add(newSelectItemInCart);
            newSelectItemInCart.showQuantity();
        } else {
            cartItem.get().addOneMore();
        }
        recalculateTotalPrice();
    }

    /**
     * Удалить товар из корзины
     *
     * @param removeItem товар, который требуется удалить из корзины
     * @return количества данной позиции в корзине.
     */
    public void removeItem(Item removeItem) {
        Optional<SelectItemInCart> cartItem = findItemById(removeItem.getId());
        Integer countItem = null;
        if (cartItem.isEmpty()) {
            countItem = 0;
        } else {
            countItem = cartItem.get().removeOne();
            if (countItem.intValue() == 0) {
                items.remove(items);
            }
        }
        recalculateTotalPrice();
    }

    /**
     * Показать итоговую цену всех выбранных товаров в корзине
     *
     * @return итоговая цена всех товаров
     */
    public Money showTotalPrice() {
        return totalPrice;
    }

    public Collection<SelectItemInCart> showSelectItemInCart() {
        return items;
    }

    /**
     * Подсчитать итоговую цену
     *
     * @return итоговая цена
     */
    private Float recalculateTotalPrice() {
        return items.stream()
                .map(p -> p.showTotalPrice())
                .map(p -> totalPrice.add(new Money(p.floatValue())))
                .findFirst()
                .get()
                .value();
    }

    /**
     * Найти товар в корзине по id
     *
     * @param idItem айди товара
     * @return товар в корзине
     */
    private Optional<SelectItemInCart> findItemById(UUID idItem) {
        return items.stream()
                .filter(p -> p.getItem().getId().equals(idItem))
                .findFirst();
    }
}

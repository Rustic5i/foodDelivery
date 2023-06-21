package ru.rbaratov.fooddelivery.orders.context.domain;

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
public class Cart extends AbstractDomain {

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
    private Set<ItemInCart> items = new HashSet<>();

    public Cart(Buyer buyer, Set<ItemInCart> items) {
        Objects.requireNonNull(buyer, "Корзина не может быть создана без юзера");
        this.buyer = buyer;
        this.items = items;
        totalPrice = recalculateTotalPrice();
    }

    /**
     * Добавить товар в корзину
     *
     * @param addItem товар, который требуется добавить в корзину
     */
    public void addItem(Item addItem) {
        Optional<ItemInCart> cartItem = findItemById(addItem.getId());
        if (cartItem.isEmpty()) {
            ItemInCart newItemInCart = new ItemInCart(addItem);
            items.add(newItemInCart);
            newItemInCart.showQuantity();
        } else {
            cartItem.get().addOneMore();
        }
        totalPrice = recalculateTotalPrice();
    }

    /**
     * Удалить товар из корзины
     *
     * @param removeItem товар, который требуется удалить из корзины
     * @return количества данной позиции в корзине.
     */
    public void removeItem(UUID removeItem) {
        Optional<ItemInCart> cartItem = findItemById(removeItem);
        Integer countItem = null;
        if (cartItem.isEmpty()) {
            countItem = 0;
        } else {
            countItem = cartItem.get().removeOne();
            if (countItem.intValue() == 0) {
                items.remove(items);
            }
        }
        totalPrice = recalculateTotalPrice();
    }

    /**
     * Показать итоговую цену всех выбранных товаров в корзине
     *
     * @return итоговая цена всех товаров
     */
    public Money showTotalPrice() {
        return totalPrice;
    }

    public Collection<ItemInCart> showSelectItemInCart() {
        return items;
    }

    /**
     * Подсчитать итоговую цену
     *
     * @return итоговая цена
     */
    private Money recalculateTotalPrice() {
        Money totalPrice = new Money(0);
        for (ItemInCart itemInCart : items) {
            Float totalPriceitem = itemInCart.showTotalPrice();
            totalPrice.add(new Money(totalPriceitem));
        }
        return totalPrice;
    }

    /**
     * Найти товар в корзине по id
     *
     * @param idItem айди товара
     * @return товар в корзине
     */
    private Optional<ItemInCart> findItemById(UUID idItem) {
        return items.stream()
                .filter(p -> p.getItem().getId().equals(idItem))
                .findFirst();
    }

    public static Cart revival(UUID id, Buyer buyer, Set<ItemInCart> items) {
        Cart cart = new Cart(buyer, items);
        cart.id = id;
        return cart;
    }
}

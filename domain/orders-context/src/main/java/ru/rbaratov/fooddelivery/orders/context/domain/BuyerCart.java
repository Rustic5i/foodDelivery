package ru.rbaratov.fooddelivery.orders.context.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.Money;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Корзина покупателя
 */
@Entity
@Table(name = "buyers_carts")
public class BuyerCart extends AbstractEntity {

    /**
     * Пользователь
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Money totalPrice = new Money(0);

    /**
     * Список выбранных товаров
     */
    @OneToMany(mappedBy = "buyerCart")
    private Set<SelectItemInCart> items = new HashSet<>();

    protected BuyerCart() {
    }

    public BuyerCart(Buyer buyer) {
        Objects.requireNonNull(buyer, "Корзина не может быть создана без юзера");
        this.buyer = buyer;
    }

    /**
     * Добавить товар в корзину
     *
     * @param addSelectItem товар, который требуется добавить в корзину
     */
    public void addItem(SelectItem addSelectItem) {
        Optional<SelectItemInCart> cartItem = findItemById(addSelectItem.getId());
        if (cartItem.isEmpty()) {
            SelectItemInCart newSelectItemInCart = new SelectItemInCart(addSelectItem);
            items.add(newSelectItemInCart);
            newSelectItemInCart.getQuantity();
        } else {
            cartItem.get().addOneMore();
        }
        recalculateTotalPrice();
    }

    /**
     * Удалить товар из корзины
     *
     * @param removeSelectItem товар, который требуется удалить из корзины
     * @return количества данной позиции в корзине.
     */
    public void removeItem(SelectItem removeSelectItem) {
        Optional<SelectItemInCart> cartItem = findItemById(removeSelectItem.getId());
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
     * Подсчитать итоговую цену
     *
     * @return итоговая цена
     */
    private Float recalculateTotalPrice() {
        return items.stream()
                .map(p -> p.getTotalPrice())
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

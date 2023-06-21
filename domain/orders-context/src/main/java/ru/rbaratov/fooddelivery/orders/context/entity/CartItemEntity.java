package ru.rbaratov.fooddelivery.orders.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

import java.util.UUID;

/**
 * Выбранный товар в корзине.
 * Связь корзина-товар
 */
@Entity
@Table(name = "cart_item")
public class CartItemEntity extends AbstractEntity {

    /**
     * Товар
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false, updatable = false, insertable = false)
    private ItemEntity item;

    @Column(name = "item_id")
    private UUID idItem;

    /**
     * Количество товара
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Корзина
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity buyerCart;

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    public ItemEntity getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartEntity getBuyerCart() {
        return buyerCart;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public void setIdItem(UUID idItem) {
        this.idItem = idItem;
    }

    public void setBuyerCart(CartEntity buyerCart) {
        this.buyerCart = buyerCart;
    }
}

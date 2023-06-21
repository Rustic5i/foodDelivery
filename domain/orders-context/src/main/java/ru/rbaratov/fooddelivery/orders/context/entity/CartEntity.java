package ru.rbaratov.fooddelivery.orders.context.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Корзина покупателя
 */
@Entity
@Table(name = "carts")
public class CartEntity extends AbstractEntity {

    /**
     * Пользователь
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id", unique = true)
    private BuyerEntity buyer;

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    /**
     * Список выбранных товаров
     */
    @OneToMany(mappedBy = "buyerCart", cascade = CascadeType.ALL)
    private Set<CartItemEntity> selectItems = new HashSet<>();

    public BuyerEntity getBuyer() {
        return buyer;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<CartItemEntity> getCartItemEntity() {
        return selectItems;
    }

    public void setSelectItems(Set<CartItemEntity> selectItems) {
        this.selectItems = selectItems;
    }

    public void setBuyer(BuyerEntity buyer) {
        this.buyer = buyer;
    }
}

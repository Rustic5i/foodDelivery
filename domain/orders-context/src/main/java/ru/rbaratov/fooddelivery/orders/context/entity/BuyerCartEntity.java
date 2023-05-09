package ru.rbaratov.fooddelivery.orders.context.entity;

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

import static java.lang.Float.valueOf;

/**
 * Корзина покупателя
 */
@Entity
@Table(name = "buyers_carts")
public class BuyerCartEntity extends AbstractEntity {

    /**
     * Пользователь
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private BuyerEntity buyer;

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice = valueOf(0);

    /**
     * Список выбранных товаров
     */
    @OneToMany(mappedBy = "buyerCart")
    private Set<SelectItemInCartEntity> selectItems = new HashSet<>();

    public BuyerEntity getBuyer() {
        return buyer;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<SelectItemInCartEntity> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(Set<SelectItemInCartEntity> selectItems) {
        this.selectItems = selectItems;
    }
}

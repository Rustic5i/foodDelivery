package ru.rbaratov.fooddelivery.domain.selected.product.basket;

import lombok.Getter;
import lombok.Setter;
import ru.rbaratov.fooddelivery.domain.AbstractSelectedProduct;
import ru.rbaratov.fooddelivery.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * Корзина
 */
@Entity(name = "baskets")
@Getter
@Setter
public class Basket extends AbstractSelectedProduct {

    /**
     * Пользователь
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

}

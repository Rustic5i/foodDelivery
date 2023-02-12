package ru.rbaratov.fooddelivery.domain.selected.product.order;


import lombok.Getter;
import lombok.Setter;
import ru.rbaratov.fooddelivery.domain.AbstractSelectedProduct;
import ru.rbaratov.fooddelivery.domain.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Заказ
 */
@Entity(name = "orders")
@Getter
@Setter
public class Order extends AbstractSelectedProduct {

    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Статус заказа
     */
    @Column(name = "state", nullable = false)
    private OrderStatus state;

    /**
     * Оплачен ли заказ
     */
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

}

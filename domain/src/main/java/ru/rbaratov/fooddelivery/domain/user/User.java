package ru.rbaratov.fooddelivery.domain.user;

import lombok.Getter;
import lombok.Setter;
import ru.rbaratov.fooddelivery.domain.AbstractEntity;
import ru.rbaratov.fooddelivery.domain.selected.product.order.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * Пользователь
 */
@Entity(name = "users")
@Getter
@Setter
public class User extends AbstractEntity {

    /**
     * Имя пользователя
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;

    /**
     * Активный пользователь или заблокированный
     */
    @Column(name = "is_active")
    private boolean isActive = true;

    /**
     * Список заказов
     */
    @OneToMany(mappedBy = "user")
    private Collection<Order> orders = new ArrayList<>();

    /**
     * Добавить заказ
     *
     * @param order заказ
     */
    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        order.setUser(this);
        orders.add(order);
    }
}

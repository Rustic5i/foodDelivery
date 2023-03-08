//package ru.rbaratov.fooddelivery.domain.user;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import org.jetbrains.annotations.NotNull;
//import ru.rbaratov.fooddelivery.domain.AbstractEntity;
//import ru.rbaratov.fooddelivery.domain.selected.product.order.Order;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
///**
// * Пользователь
// *
// * @author rbaratov
// */
//@Entity(name = "users")
//public class User extends AbstractEntity {
//
//    /**
//     * Имя пользователя
//     */
//    @Column(name = "first_name", nullable = false, unique = true)
//    private String firstName;
//
//    /**
//     * Фамилия пользователя
//     */
//    @Column(name = "last_name")
//    private String lastName;
//
//    /**
//     * Отчество
//     */
//    @Column(name = "patronymic")
//    private String patronymic;
//
//    /**
//     * Электронная почта
//     */
//    @Column(name = "email")
//    private String email;
//
//    /**
//     * Дата рождения
//     */
//    @Column(name = "birthday")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date birthday;
//
//    /**
//     * Пароль
//     */
//    @Column(name = "password")
//    private String password;
//
//    /**
//     * Активный пользователь или заблокированный
//     */
//    @Column(name = "is_active")
//    private boolean isActive = true;
//
//    /**
//     * Список заказов
//     */
//    @OneToMany(mappedBy = "user")
//    private Collection<Order> orders = new ArrayList<>();
//
//    /**
//     * Добавить заказ
//     *
//     * @param order заказ
//     */
//    public void addOrder(@NotNull Order order) {
//        order.setUser(this);
//        orders.add(order);
//    }
//
//}

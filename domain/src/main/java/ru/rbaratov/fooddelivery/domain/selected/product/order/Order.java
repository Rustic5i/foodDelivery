//package ru.rbaratov.fooddelivery.domain.selected.product.order;
//
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import ru.rbaratov.fooddelivery.domain.AbstractEntity;
//import ru.rbaratov.fooddelivery.domain.user.User;
//
//import java.util.List;
//
///**
// * Заказ
// */
//@Entity(name = "orders")
//public class Order extends AbstractEntity {
//
//    /**
//     * Пользователь
//     */
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    /**
//     * Статус заказа
//     */
//    @Column(name = "state", nullable = false)
//    private OrderStatus state;
//
//    /**
//     * Оплачен ли заказ
//     */
//    @Column(name = "is_paid", nullable = false)
//    private boolean isPaid;
//
//    @OneToMany(mappedBy = "order")
//    private List<OrderProduct> products = new java.util.ArrayList<>();
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public OrderStatus getState() {
//        return state;
//    }
//
//    public void setState(OrderStatus state) {
//        this.state = state;
//    }
//
//    public boolean isPaid() {
//        return isPaid;
//    }
//
//    public void setPaid(boolean paid) {
//        isPaid = paid;
//    }
//
//    public List<OrderProduct> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<OrderProduct> products) {
//        this.products = products;
//    }
//}

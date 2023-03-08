//package ru.rbaratov.fooddelivery.domain.selected.product.basket;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import ru.rbaratov.fooddelivery.domain.AbstractEntity;
//import ru.rbaratov.fooddelivery.domain.product.Product;
//import ru.rbaratov.fooddelivery.domain.user.User;
//
//import java.util.List;
//
///**
// * Корзина
// */
//@Entity(name = "baskets")
//public class Basket extends AbstractEntity {
//
//    /**
//     * Пользователь
//     */
//    @OneToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    /**
//     * Итоговая цена
//     */
//    @Column(name = "total_price", nullable = false)
//    private Integer totalPrice;
//
//    /**
//     * Список товаров
//     */
//    @OneToMany(mappedBy = "basket")
//    private List<BasketProduct> products = new java.util.ArrayList<>();
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Integer getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Integer totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public List<BasketProduct> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<BasketProduct> products) {
//        this.products = products;
//    }
//}

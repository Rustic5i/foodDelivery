//package ru.rbaratov.fooddelivery.domain.selected.product.basket;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import ru.rbaratov.fooddelivery.domain.AbstractEntity;
//import ru.rbaratov.fooddelivery.domain.product.Product;
//
///**
// * Связь корзина-продуктов
// *
// * @author rbaratov
// */
//@Entity
//@Table(name = "basket_product")
//public class BasketProduct extends AbstractEntity {
//
//    /**
//     * Продукт/товар
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    /**
//     * Корзина
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "basket_id")
//    private Basket basket;
//
//    /**
//     * Количество самого товара
//     */
//    @Column(name = "quantity")
//    private Long quantity;
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Basket getBasket() {
//        return basket;
//    }
//
//    public void setBasket(Basket basket) {
//        this.basket = basket;
//    }
//
//    public Long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Long quantity) {
//        this.quantity = quantity;
//    }
//}

//package ru.rbaratov.fooddelivery.domain.selected.product.order;
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
// * Связь заказ-продукты
// */
//@Entity
//@Table(name = "order_product")
//public class OrderProduct extends AbstractEntity {
//
//    /**
//     * Продукт/товар
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    /**
//     * Заказ
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
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
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
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

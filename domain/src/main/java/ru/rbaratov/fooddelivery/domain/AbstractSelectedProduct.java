package ru.rbaratov.fooddelivery.domain;

import lombok.Getter;
import lombok.Setter;
import ru.rbaratov.fooddelivery.domain.product.Product;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Выбранные товары
 */
@Getter
@Setter
public abstract class AbstractSelectedProduct extends AbstractEntity {

    /**
     * Список товаров
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "products_id", nullable = false))
    private List<Product> products = new java.util.ArrayList<>();

    /**
     * Итоговая цена
     */
    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

}

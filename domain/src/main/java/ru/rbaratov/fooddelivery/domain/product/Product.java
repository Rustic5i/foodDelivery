package ru.rbaratov.fooddelivery.domain.product;

import ru.rbaratov.fooddelivery.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Товар
 */
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    /**
     * Наименование товара
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Цена
     */
    @Column(name = "price")
    private Integer price;

    /**
     * Старая цена
     */
    @Column(name = "old_price")
    private Integer oldPrice;

    /**
     * Новый товар
     */
    @Column(name = "new_product")
    private boolean newProduct;

    /**
     * Горячий товар
     */
    @Column(name = "hot_product")
    private boolean hotProduct;

    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
}
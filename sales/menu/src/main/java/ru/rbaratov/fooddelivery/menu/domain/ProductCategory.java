package ru.rbaratov.fooddelivery.menu.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.CategoryName;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Категория товара
 */
@Entity
@Table(name = "product_categories")
public final class ProductCategory extends AbstractEntity {

    /**
     * Имя категории
     */
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
    private CategoryName name;

    /**
     * Список продуктов
     */
    @OneToMany(mappedBy = "category")
    private Collection<Product> products = new ArrayList<>();

    protected ProductCategory() {

    }

    public ProductCategory(CategoryName name) {
        if (name == null) {
            throw new RuntimeException("Названия категории товара не было указано");
        }
        this.name = name;
    }

    public CategoryName nameCategory() {
        return name;
    }
}

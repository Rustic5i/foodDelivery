package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Товар
 */
@Entity
@Table(name = "items")
public final class Item extends AbstractEntity {

    /**
     * Имя товара
     */
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
    private ItemName name;

    /**
     * Цена
     */
    @AttributeOverride(name = "money", column = @Column(name = "price", nullable = false))
    private Money price;

    /**
     * Старая цена
     */
    @AttributeOverride(name = "money", column = @Column(name = "old_price"))
    private Money oldPrice;

    /**
     * Является ли товар новым
     */
    @Column(name = "is_new_item", nullable = false)
    private boolean isNewItem = false;

    /**
     * Описание
     */
    @AttributeOverride(name = "description", column = @Column(name = "description", nullable = false))
    private ItemDescription description;

    /**
     * Размер, вес, обьем и тд
     */
    @AttributeOverrides({
            @AttributeOverride(name = "size", column = @Column(name = "size", nullable = false)),
            @AttributeOverride(name = "sizeUnit", column = @Column(name = "size_unit", nullable = false))
    })
    private ItemSize size;

    /**
     * Является ли составным товаром или набором
     */
    @Column(name = "is_set", nullable = false)
    private boolean isSet = false;

    /**
     * Категория товара
     */
    @ManyToOne
    @JoinColumn(name = "category")
    private ItemCategory category;

    /**
     * В каком меню состоит
     */
    @ManyToMany(mappedBy = "items")
    private Set<Menu> menu = new HashSet<>();

    protected Item() {

    }

    public Item(@NonNull ItemName name,
                @NonNull Money price,
                @NonNull ItemDescription description,
                @NonNull ItemSize size,
                @NonNull ItemCategory category) {
        if (name == null) {
            throw new RuntimeException("Товар не может быть без имени");
        }
        if (price == null) {
            throw new RuntimeException("Товар не может быть без цены");
        }
        if (description == null) {
            throw new RuntimeException("Товар не может быть без описания");
        }
        if (size == null) {
            throw new RuntimeException("Товар не может быть без указания размера");
        }
        if (category == null) {
            throw new RuntimeException("У товара не указана категория");
        }
        if (category.getId() == null) {
            throw new RuntimeException(
                    MessageFormat.format("Ошибка при создания товара с именем {0}. Категория товара с именем {1} еще не создана или не сохранена",
                            name.value(), category.nameCategory().value()));
        }
        this.name = name;
        this.price = price;
        this.description = description;
        this.size = size;
        this.category = category;
    }

    /**
     * Обновить или изменить цену товара
     *
     * @param newPrice новая цена
     */
    public void changePrice(Money newPrice) {
        Objects.requireNonNull(newPrice, MessageFormat.format("Цена для товара {0} не была указана", name.value()));
        if (newPrice.value() < 1) {
            throw new RuntimeException("Цена товара не может быть меньше единицы");
        }
        price = newPrice;
    }

    /**
     * Получить цену товара
     *
     * @return цена товара
     */
    public Float getPrice() {
        return price.value();
    }

    /**
     * Получить имя товара
     *
     * @return имя товара
     */
    public ItemName getName() {
        return name;
    }
}

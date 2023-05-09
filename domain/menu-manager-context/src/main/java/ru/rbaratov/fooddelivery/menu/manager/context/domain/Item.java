package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item extends AbstractDomain {

    /**
     * Имя товара
     */
    private ItemName name;

    /**
     * Цена
     */
    private Money price;

    /**
     * Старая цена
     */
    private Money oldPrice;

    /**
     * Является ли товар новым
     */
    private boolean isNewItem = false;

    /**
     * Описание
     */
    private ItemDescription description;

    /**
     * Размер, вес, обьем и тд
     */
    private ItemSize size;

    /**
     * Является ли составным товаром или набором
     */
    private boolean isSet = false;

    /**
     * Категория товара
     */
    private ItemCategory category;

    /**
     * В каком меню состоит
     */
    private Set<MenuEntity> menu = new HashSet<>();

    public Item(@NonNull ItemName name,
                @NonNull Money price,
                @NonNull ItemDescription description,
                @NonNull ItemSize size,
                @NonNull ItemCategory category,
                Money oldPrice) {
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
        this.oldPrice = oldPrice;
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

    public ItemName getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Money getOldPrice() {
        return oldPrice;
    }

    public boolean isNewItem() {
        return isNewItem;
    }

    public ItemDescription getDescription() {
        return description;
    }

    public ItemSize getSize() {
        return size;
    }

    public boolean isSet() {
        return isSet;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public Set<MenuEntity> getMenu() {
        return menu;
    }
}

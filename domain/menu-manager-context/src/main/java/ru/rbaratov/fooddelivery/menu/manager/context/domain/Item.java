package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemDescription;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemSize;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
    private Category category;

//    /**
//     * В каком меню состоит
//     */
//    private Set<Menu> menu = new HashSet<>();

    private Item() {
    }

    public Item(@NonNull ItemName name,
                @NonNull Money price,
                @NonNull ItemDescription description,
                @NonNull ItemSize size,
                @NonNull Category category,
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
        if (category == null || category.getName() == null) {
            throw new RuntimeException("У товара не указана категория");
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
        return oldPrice == null ? null : oldPrice;
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

    public Category getCategory() {
        return category;
    }

//    public Set<Menu> getMenu() {
//        return menu;
//    }

    public static IdBuilder startRevival() {
        return new Builder();
    }

    public interface IdBuilder {
        NameBuilder requiredId(UUID id);
    }

    public interface NameBuilder {
        PriceBuilder requiredName(ItemName name);
    }

    public interface PriceBuilder {
        OldPriceBuilder requiredPrice(Money price);
    }

    public interface OldPriceBuilder {
        ItemDescriptionBuilder requiredOldPrice(Money oldPrice);
    }

    public interface ItemDescriptionBuilder {
        ItemSizeBuilder requiredItemDescription(ItemDescription itemDescription);
    }

    public interface ItemSizeBuilder {
        CategoryBuilder requiredItemSize(ItemSize itemSize);
    }

    public interface CategoryBuilder {
        FinalBuilder requiredCategory(Category category);
    }


    public interface FinalBuilder {
        FinalBuilder setIsNewItem(boolean isNewItem);

//        FinalBuilder setMenu(Set<Menu> menu);

        Item revive();
    }

    private static class Builder implements IdBuilder, NameBuilder,PriceBuilder,OldPriceBuilder,
            ItemDescriptionBuilder,ItemSizeBuilder, CategoryBuilder,FinalBuilder {

        private Item item = new Item();

        @Override
        public NameBuilder requiredId(UUID id) {
            item.id = id;
            return this;
        }

        @Override
        public PriceBuilder requiredName(ItemName name) {
            item.name = name;
            return this;
        }

        @Override
        public OldPriceBuilder requiredPrice(Money price) {
            item.price = price;
            return this;
        }

        @Override
        public ItemDescriptionBuilder requiredOldPrice(Money oldPrice) {
            item.oldPrice = oldPrice;
            return this;
        }

        @Override
        public ItemSizeBuilder requiredItemDescription(ItemDescription itemDescription) {
            item.description = itemDescription;
            return this;
        }

        @Override
        public CategoryBuilder requiredItemSize(ItemSize itemSize) {
            item.size = itemSize;
            return this;
        }

        @Override
        public FinalBuilder requiredCategory(Category category) {
            item.category= category;
            return this;
        }

        @Override
        public FinalBuilder setIsNewItem(boolean isNewItem) {
            item.isNewItem = isNewItem;
            return this;
        }

//        @Override
//        public FinalBuilder setMenu(Set<Menu> menu) {
//            item.menu = menu;
//            return this;
//        }

        @Override
        public Item revive() {
            return item;
        }
    }
}

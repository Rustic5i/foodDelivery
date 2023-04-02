package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Меню
 */
@Entity
@Table(name = "menu")
public final class Menu extends AbstractEntity {

    /**
     * Названия меню
     */
    @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, unique = true))
    private MenuName name;

    /**
     * Список товара
     */
    @ManyToMany
    @JoinTable(name = "menu_item", joinColumns = {@JoinColumn(name = "menu_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> items = new HashSet<>();

    protected Menu() {
    }

    public Menu(MenuName name) {
        this.name = name;
    }

    /**
     * Добавить в меню новый товар
     *
     * @param newItem новый товар
     */
    public void addNewItem(@NonNull Item newItem) {
        if (newItem == null) {
            throw new RuntimeException("В меню нельзя добавить пустой товар");
        }
        if (newItem.getId() == null) {
            throw new RuntimeException(MessageFormat.format("Нельзя добавить в меню {0} не сохраненный товар с именем {1}", name.value(), newItem.getName()));
        }
        if (items.contains(newItem)) {
            throw new RuntimeException("В меню уже присутствует данный товар");
        }
        items.add(newItem);
    }

    /**
     * Удалить товар из меню
     *
     * @param item
     */
    public void deleteItem(@NonNull Item item) {
        if (item == null) {
            throw new RuntimeException("Из меню нельзя удалить пусто товар");
        }
        if (!items.contains(item)) {
            throw new RuntimeException("Нельзя удалить из меню товар которого там нет");
        }
        items.remove(item);
    }


    /**
     * Показать весь товар.
     * Возвращает не изменяемый список товаров
     *
     * @return Возвращает не изменяемый список товаров
     */
    public Collection<Item> showAllItem() { //todo фикс Object убрать, считывание данных будет через DTO CQRS
        return Collections.unmodifiableCollection(items);
    }

    /**
     * Найти товар по имени
     *
     * @param itemName имя товара
     * @return найденный товар
     */
    public Optional<Item> findItemByName(ItemName itemName) {
        if (itemName == null) {
            return Optional.empty();
        }
        return items.stream().filter(p -> p.getName().equals(itemName)).findFirst();
    }

    /**
     * Получить названия меню
     *
     * @return названия меню
     */
    public MenuName getMenuName() { //TODO убрать
        return name;
    }
}

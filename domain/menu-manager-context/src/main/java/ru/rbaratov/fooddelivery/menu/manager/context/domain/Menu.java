package ru.rbaratov.fooddelivery.menu.manager.context.domain;

import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.common.domain.AbstractDomain;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Menu extends AbstractDomain {

    /**
     * Названия меню
     */
    private String name;

    /**
     * Список товара
     */
    private Set<Item> items = new HashSet<>();

    private Menu() {
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
//        if (newItem.getId() == null) {
//            throw new RuntimeException(MessageFormat.format("Нельзя добавить в меню {0} не сохраненный товар с именем {1}", name.value(), newItem.getName().value()));
//        }
        if (items.contains(newItem)) {
            throw new RuntimeException("В меню уже присутствует данный товар");
        }
        items.add(newItem);
    }

    /**
     * Удалить товар из меню
     *
     * @param idItem
     */
    public void deleteItem(@NonNull UUID idItem) {
        if (idItem == null) {
            return;
        }
        Optional<Item> item = items.stream().filter(i -> i.getId() == idItem).findFirst();
        if (!item.isPresent()) {
            throw new RuntimeException("Нельзя удалить из меню товар которого там нет");
        }
        items.remove(item.get());
    }


    /**
     * Показать весь товар.
     * Возвращает не изменяемый список товаров
     *
     * @return Возвращает не изменяемый список товаров
     */
    public Collection<Item> showAllItem() {
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

    public String getName() {
        return name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public static ItemsBuilder startRevival(UUID id, String name) {
        return new Builder(id, name);
    }

    public interface ItemsBuilder {
        FinalBuilder requiredItems(Set<Item> items);
    }

    public interface FinalBuilder {
        Menu revive();
    }

    private static class Builder implements ItemsBuilder, FinalBuilder {

        private Menu menu = new Menu();

        public Builder(UUID id, String name) {
            this.menu.id = id;
            this.menu.name = name;
        }

        @Override
        public FinalBuilder requiredItems(Set<Item> items) {
            this.menu.items = items;
            return this;
        }

        @Override
        public Menu revive() {
            return menu;
        }
    }
}

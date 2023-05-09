package ru.rbaratov.fooddelivery.menu.manager.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Меню
 */
@Entity
@Table(name = "menu")
public final class MenuEntity extends AbstractEntity {

    /**
     * Названия меню
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Список товара
     */
    @ManyToMany
    @JoinTable(name = "menu_item", joinColumns = {@JoinColumn(name = "menu_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<ItemEntity> items = new HashSet<>();

    public MenuEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<ItemEntity> items) {
        this.items = items;
    }
}

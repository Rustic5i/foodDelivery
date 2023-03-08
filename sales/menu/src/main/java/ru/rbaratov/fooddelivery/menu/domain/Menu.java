package ru.rbaratov.fooddelivery.menu.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductName;

import java.text.MessageFormat;
import java.util.Collection;
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
    @JoinTable(
            name = "menu_product",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products = new HashSet<>();

    protected Menu() {
    }

    public Menu(MenuName name) {
        this.name = name;
    }

    /**
     * Добавить в меню новый товар
     *
     * @param newProduct новый товар
     */
    public void addProduct(@NonNull Product newProduct) {
        if (newProduct == null) {
            throw new RuntimeException("В меню нельзя добавить пустой товар");
        }
        if (newProduct.getId() == null) {
            throw new RuntimeException(
                    MessageFormat.format("Нельзя добавить в меню {0} не сохраненный товар с именем {1}", name.value(), newProduct.getName()));
        }
        if (products.contains(newProduct)) {
            throw new RuntimeException("В меню уже присутствует данный товар");
        }
        products.add(newProduct);
    }

    /**
     * Удалить товар из меню
     *
     * @param product
     */
    public void deleteProduct(@NonNull Product product) {
        if (product == null) {
            throw new RuntimeException("Из меню нельзя удалить пусто товар");
        }
        if (!products.contains(product)) {
            throw new RuntimeException("Нельзя удалить из меню товар которого там нет");
        }
        products.remove(product);
    }


    /**
     * Показать весь товар.
     *
     * @return Возвращает не изменяемый список товаров
     */
    public Collection<Object> showAllProduct() { //todo фикс Object
        return Set.of(products.toArray());
    }

    /**
     * Найти товар по имени
     *
     * @param productName имя товара
     * @return найденный товар
     */
    public Optional<Product> findProductByName(ProductName productName) {
        if (productName == null) {
            return Optional.empty();
        }
        return products.stream().filter(p -> p.getName().equals(productName)).findFirst();
    }

    /**
     * Получить названия меню
     *
     * @return названия меню
     */
    public MenuName getMenuName() {
        return name;
    }
}

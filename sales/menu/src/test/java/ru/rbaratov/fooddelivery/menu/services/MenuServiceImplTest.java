package ru.rbaratov.fooddelivery.menu.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.show-sql=true",
})
@Transactional
class MenuServiceImplTest extends TestData {

    @Autowired
    private MenuService menuService;

    @Test
    void createNewMenu() {
        final MenuName menuName = new MenuName("Меню холодных напитков");

        menuService.createNewMenu(menuName);
        Optional<Menu> menu = menuRepository.findByName(menuName);

        Assertions.assertNotNull(menu.get());
        Assertions.assertEquals(menuName, menu.get().getMenuName());
    }

    @Test
    void addProductInMenu() {
        Menu menu = createAndSaveMenu("Горячие роллы");
        Product product1 = createAndSaveProduct("Юдзо Рору", "Роллы");
        Product product2 = createAndSaveProduct("Сяки Рору", "Роллы");
        Product product3 = createAndSaveProduct("Пицца 4 сыра", "Пицца");

        menuService.addProductInMenu(product1, menu.getMenuName());
        menuService.addProductInMenu(product2, menu.getMenuName());
        menuService.addProductInMenu(product3, menu.getMenuName());

        Optional<Menu> actualMenu = menuRepository.findByName(menu.getMenuName());

        Assertions.assertTrue(actualMenu.isPresent());
        Assertions.assertEquals(actualMenu.get().getMenuName(), menu.getMenuName());
        Assertions.assertEquals(actualMenu.get().showAllProduct().stream().count(), 3);
        Assertions.assertTrue(actualMenu.get().findProductByName(product1.getName()).isPresent());
        Assertions.assertTrue(actualMenu.get().findProductByName(product2.getName()).isPresent());
        Assertions.assertTrue(actualMenu.get().findProductByName(product3.getName()).isPresent());
    }

    @Test
    void deleteProduct() {
        Menu menu = createAndSaveMenu("Горячее меню");
        Product product = createAndSaveProduct("Пицца 4 сыра", "Пиццы");

        menu.addProduct(product);
        menuRepository.save(menu);

        Assertions.assertTrue(menu.findProductByName(product.getName()).isPresent());

        menuService.removeProductFromMenu(product, menu.getMenuName());

        Assertions.assertTrue(menu.findProductByName(product.getName()).isEmpty());

    }

    @Test
    void giveMenu() {
        Menu menu = createAndSaveMenu("Горячие роллы");
        Product product1 = createAndSaveProduct("Юдзо Рору", "Роллы");
        Product product2 = createAndSaveProduct("Сяки Рору", "Роллы");
        Product product3 = createAndSaveProduct("Пицца 4 сыра", "Пицца");
        menu.addProduct(product1);
        menu.addProduct(product2);
        menu.addProduct(product3);
        menuRepository.save(menu);

        Menu actualMenu = menuService.giveMenu(menu.getMenuName());

        Assertions.assertNotNull(actualMenu);
        Assertions.assertEquals(actualMenu.getMenuName(), menu.getMenuName());
        Assertions.assertEquals(actualMenu.showAllProduct().stream().count(), 3);
        Assertions.assertTrue(actualMenu.findProductByName(product1.getName()).isPresent());
        Assertions.assertTrue(actualMenu.findProductByName(product2.getName()).isPresent());
        Assertions.assertTrue(actualMenu.findProductByName(product3.getName()).isPresent());
    }

    @Configuration
    @ComponentScan(basePackages = "ru.rbaratov.fooddelivery.menu")
    @EntityScan("ru.rbaratov.fooddelivery.menu.domain")
    public static class Config {
    }
}
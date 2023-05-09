//package ru.rbaratov.fooddelivery.menu.manager.context.services;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.Item;
//import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
//
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest(properties = {
//        "spring.liquibase.enabled=false",
//        "spring.jpa.show-sql=true",
//})
//@Transactional
//class MenuServiceImplTest extends TestData {
//
//    @Autowired
//    private MenuService menuService;
//
//    @Test
//    void createNewMenu() {
//        final MenuName menuName = new MenuName("Меню холодных напитков");
//
//        menuService.createNewMenu(menuName);
//        Optional<MenuEntity> menu = menuRepository.findByName(menuName);
//
//        Assertions.assertNotNull(menu.get());
//        Assertions.assertEquals(menuName, menu.get().showMenuName());
//    }
//
//    @Test
//    void addItemInMenu() {
//        MenuEntity menu = createAndSaveMenu("Горячие роллы");
//        Item item1 = createAndSaveItem("Юдзо Рору", "Роллы");
//        Item item2 = createAndSaveItem("Сяки Рору", "Роллы");
//        Item item3 = createAndSaveItem("Пицца 4 сыра", "Пицца");
//
//        menuService.addItemInMenu(item1, menu.showMenuName());
//        menuService.addItemInMenu(item2, menu.showMenuName());
//        menuService.addItemInMenu(item3, menu.showMenuName());
//
//        Optional<MenuEntity> actualMenu = menuRepository.findByName(menu.showMenuName());
//
//        Assertions.assertTrue(actualMenu.isPresent());
//        Assertions.assertEquals(actualMenu.get().showMenuName(), menu.showMenuName());
//        Assertions.assertEquals(actualMenu.get().showAllItem().stream().count(), 3);
//        Assertions.assertTrue(actualMenu.get().findItemByName(item1.showName()).isPresent());
//        Assertions.assertTrue(actualMenu.get().findItemByName(item2.showName()).isPresent());
//        Assertions.assertTrue(actualMenu.get().findItemByName(item3.showName()).isPresent());
//    }
//
//    @Test
//    void deleteItem() {
//        MenuEntity menu = createAndSaveMenu("Горячее меню");
//        Item item = createAndSaveItem("Пицца 4 сыра", "Пиццы");
//
//        menu.addNewItem(item);
//        menuRepository.save(menu);
//
//        Assertions.assertTrue(menu.findItemByName(item.showName()).isPresent());
//
//        menuService.removeItemFromMenu(item, menu.showMenuName());
//
//        Assertions.assertTrue(menu.findItemByName(item.showName()).isEmpty());
//
//    }
//
//    @Test
//    void giveMenu() {
//        MenuEntity menu = createAndSaveMenu("Горячие роллы");
//        Item item1 = createAndSaveItem("Юдзо Рору", "Роллы");
//        Item item2 = createAndSaveItem("Сяки Рору", "Роллы");
//        Item item3 = createAndSaveItem("Пицца 4 сыра", "Пицца");
//        menu.addNewItem(item1);
//        menu.addNewItem(item2);
//        menu.addNewItem(item3);
//        menuRepository.save(menu);
//
//        MenuEntity actualMenu = menuService.giveMenu(menu.showMenuName());
//
//        Assertions.assertNotNull(actualMenu);
//        Assertions.assertEquals(actualMenu.showMenuName(), menu.showMenuName());
//        Assertions.assertEquals(actualMenu.showAllItem().stream().count(), 3);
//        Assertions.assertTrue(actualMenu.findItemByName(item1.showName()).isPresent());
//        Assertions.assertTrue(actualMenu.findItemByName(item2.showName()).isPresent());
//        Assertions.assertTrue(actualMenu.findItemByName(item3.showName()).isPresent());
//    }
//
//    @Configuration
//    @ComponentScan(basePackages = "ru.rbaratov.fooddelivery.menu.manager.context")
//    @EntityScan("ru.rbaratov.fooddelivery.menu.manager.context.domain")
//    public static class Config {
//    }
//}
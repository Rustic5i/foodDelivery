package ru.rbaratov.fooddelivery.menu.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;
import ru.rbaratov.fooddelivery.menu.factory.MenuFactory;
import ru.rbaratov.fooddelivery.menu.repository.menu.MenuRepository;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuFactory menuFactory;

    public void createNewMenu(@NonNull MenuName menuName) {
        Menu newMenu = menuFactory.createNewMenu(menuName);
        menuRepository.save(newMenu);
        LOGGER.info("Создали новое меню с название {}", menuName.value());
    }

    public void addProductInMenu(Product product, MenuName menuName) {
        Objects.requireNonNull(menuName, "При добавления товара в меню, не был передано названия меню");
        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
        if (menuOptional.isEmpty()) {
            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найден", menuName.value()));
        }
        Menu menu = menuOptional.get();
        menu.addProduct(product);
        menuRepository.save(menu);
        LOGGER.info("В меню {} добавлен новый товар {}", menuName.value(), product.getName().value());
    }

    @Override
    public void removeProductFromMenu(Product product, MenuName menuName) {
        Objects.requireNonNull(menuName, "При удаления товара из меню, не был передано названия меню");
        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
        if (menuOptional.isEmpty()) {
            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найден", menuName.value()));
        }
        Menu menu = menuOptional.get();
        menu.deleteProduct(product);
        LOGGER.info("Из меню {} был удален товар с именем {}", menuName.value(), product.getName().value());
    }

    @Override
    public Menu giveMenu(MenuName menuName) {
        Objects.requireNonNull(menuName, "При попытки показать меню, не был передано названия меню");
        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
        if (menuOptional.isEmpty()) {
            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найден", menuName.value()));
        }
        return menuOptional.get();
    }
}

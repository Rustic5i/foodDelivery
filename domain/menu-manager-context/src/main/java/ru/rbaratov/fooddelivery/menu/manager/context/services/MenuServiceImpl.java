//package ru.rbaratov.fooddelivery.menu.manager.context.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Service;
//import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.Item;
//import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
//import ru.rbaratov.fooddelivery.menu.manager.context.factory.MenuFactory;
//import ru.rbaratov.fooddelivery.menu.manager.context.repository.menu.MenuRepository;
//
//import java.text.MessageFormat;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class MenuServiceImpl implements MenuService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
//
//    @Autowired
//    private MenuRepository menuRepository;
//    @Autowired
//    private MenuFactory menuFactory;

//    public void createNewMenu(@NonNull MenuName menuName) {
//        Menu newMenu = menuFactory.createNewMenu(menuName);
//        menuRepository.save(newMenu);
//        LOGGER.info("Создали новое меню с название {}", menuName.value());
//    }

//    public void addItemInMenu(Item item, MenuName menuName) {
//        Objects.requireNonNull(menuName, "При добавлении товара в меню, не было передано название меню");
//        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
//        if (menuOptional.isEmpty()) {
//            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найдено", menuName.value()));
//        }
//        Menu menu = menuOptional.get();
//        menu.addNewItem(item);
//        menuRepository.save(menu);
//        LOGGER.info("В меню {} добавлен новый товар {}", menuName.value(), item.getName().value());
//    }

//    @Override
//    public void removeItemFromMenu(Item item, MenuName menuName) {
//        Objects.requireNonNull(menuName, "При удалении товара из меню, не было передано название меню");
//        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
//        if (menuOptional.isEmpty()) {
//            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найдено", menuName.value()));
//        }
//        Menu menu = menuOptional.get();
//        menu.deleteItem(item);
//        LOGGER.info("Из меню {} был удален товар с именем {}", menuName.value(), item.getName().value());
//    }

//    @Override
//    public Menu giveMenu(MenuName menuName) {
//        Objects.requireNonNull(menuName, "При попытке показать меню, не был передано название меню");
//        Optional<Menu> menuOptional = menuRepository.findByName(menuName);
//        if (menuOptional.isEmpty()) {
//            throw new RuntimeException(MessageFormat.format("Меню с именем {0} не найден", menuName.value()));
//        }
//        return menuOptional.get();
//    }
//}

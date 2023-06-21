package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper.MenuM;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu.MenuRepository;

@Component
public class MenuAggregateRepositoryImpl implements MenuAggregateRepository {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuM mapper;

    @Override
    public Menu getMenu() {
        MenuEntity menuEntity = menuRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("Меню не найдено"));
        return mapper.toMenu(menuEntity);
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(mapper.toMenuEntity(menu));
    }
}

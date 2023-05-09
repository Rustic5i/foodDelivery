package ru.rbaratov.fooddelivery.menu.manager.context.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.valueobject.item.MenuName;
import ru.rbaratov.fooddelivery.menu.manager.context.domain.Menu;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper.MenuAggregateMapper;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu.MenuRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class MenuAggregateRepositoryImpl implements MenuAggregateRepository {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuAggregateMapper mapper;

    @Override
    public Optional<Menu> getById(UUID id) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(id);
        if (menuEntity.isPresent()) {
            return Optional.of(mapper.toMenu(menuEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Menu> findByName(MenuName menuName) {
        Optional<MenuEntity> menuEntity = menuRepository.findByName(menuName.value());
        if (menuEntity.isPresent()) {
            return Optional.of(mapper.toMenu(menuEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(mapper.toMenuEntity(menu));
    }

    @Override
    public Boolean existsMenuByName(MenuName menuName) {
        return menuRepository.existsMenuByName(menuName.value());
    }
}

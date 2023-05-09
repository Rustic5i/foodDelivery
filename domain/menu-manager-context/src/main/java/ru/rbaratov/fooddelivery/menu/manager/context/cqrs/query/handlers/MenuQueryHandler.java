package ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.GetMenuByIdQuery;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.MenuDTO;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.mapper.MenuDTOMapper;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.MenuEntity;
import ru.rbaratov.fooddelivery.menu.manager.context.entity.repository.menu.MenuRepository;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class MenuQueryHandler implements QueryHandler {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuDTOMapper menuMapper;

    @Override
    public MenuDTO getMenuByName(GetMenuByIdQuery query) {
        final UUID menuId = query.getMenuId();
        MenuEntity menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Меню с id {0} не найдено", menuId)));
        MenuDTO menuDTO = menuMapper.toMenuDTO(menu);
        return menuDTO;
    }
}

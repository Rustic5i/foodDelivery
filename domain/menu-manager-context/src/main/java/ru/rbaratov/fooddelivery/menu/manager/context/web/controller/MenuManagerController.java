package ru.rbaratov.fooddelivery.menu.manager.context.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.Gate;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.CreateNewCategoryCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.AddNewItemToMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.commands.RemoveItemFromMenuCommand;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.GetMenuByIdQuery;
import ru.rbaratov.fooddelivery.menu.manager.context.cqrs.query.handlers.MenuQueryHandler;
import ru.rbaratov.fooddelivery.menu.manager.context.dto.MenuDTO;

import java.util.UUID;

@Controller
@RequestMapping("admin/menu")
public class MenuManagerController {

    @Autowired
    private Gate gate;
    @Autowired
    private MenuQueryHandler menuQueryHandler;

    @RequestMapping(value = "/addItem", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addItemToMenu(@RequestBody AddNewItemToMenuCommand command) {
        gate.send(command);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity removeItemToMenu(@RequestBody RemoveItemFromMenuCommand command) {
        gate.send(command);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Идентификатор не должен быть нулевым");
        }
        MenuDTO menuDTO = menuQueryHandler.getMenuByName(new GetMenuByIdQuery(id));
        return new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.OK);
    }

    @PostMapping("category/create")
    public ResponseEntity<String> createNewCategory(@RequestBody CreateNewCategoryCommand createNewCategoryCommand) {
        gate.send(createNewCategoryCommand);
        return ResponseEntity.ok("Создана новая категория товаров в меню");
    }
}

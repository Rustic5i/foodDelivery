package ru.rbaratov.fooddelivery.menu.manager.context.dto;

import java.util.Set;

public class MenuDTO {

    private String nameMenu;

    private Set<ItemDTO> items;

    public MenuDTO(String nameMenu, Set<ItemDTO> items) {
        this.nameMenu = nameMenu;
        this.items = items;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }
}

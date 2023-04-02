package ru.rbaratov.fooddelivery.orders.context.dto;

import java.util.HashSet;
import java.util.Set;

public class BuyerCartDTO {

    /**
     * Итоговая цена
     */
    private Float totalPrice;

    /**
     * Список товаров
     */
    private Set<CartItemDTO> items = new HashSet<>();

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<CartItemDTO> items) {
        this.items = items;
    }
}

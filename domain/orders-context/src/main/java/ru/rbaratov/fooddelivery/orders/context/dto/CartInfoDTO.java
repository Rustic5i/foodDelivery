package ru.rbaratov.fooddelivery.orders.context.dto;

import java.util.ArrayList;
import java.util.List;

public class CartInfoDTO {

    /**
     * Итоговая цена
     */
    private Float totalPrice;

    /**
     * Список товаров
     */
    private List<CartItemDTO> selectItems = new ArrayList<>();

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemDTO> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<CartItemDTO> selectItems) {
        this.selectItems = selectItems;
    }
}

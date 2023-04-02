package ru.rbaratov.fooddelivery.orders.context.dto;

public class CartItemDTO {
    /**
     * Товар
     */
    private ItemDTO item;

    /**
     * Количество товара
     */
    private Integer quantity = 0;

    /**
     * Итоговая цена
     */
    private Float totalPrice = 0f;

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

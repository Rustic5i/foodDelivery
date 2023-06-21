package ru.rbaratov.fooddelivery.orders.context.domain;

/**
 * Адреса покупателя
 */
public class BuyerAddress extends AbstractDomain {

    /**
     * Улица
     */
    private String street;

    /**
     * Здание, дом
     */
    private String building;

    /**
     * Квартира
     */
    private String apartment;

    /**
     * Подъезд
     */
    private String entrance;

    /**
     * Этаж
     */
    private Long floor;

    /**
     * Комментарий
     */
    private String comment;

    /**
     * Номер дома
     */
    private String homeNumber;

    /**
     * Широта
     */
    private String lat;

    /**
     * Долгота
     */
    private String lng;

    private Buyer buyer;
}

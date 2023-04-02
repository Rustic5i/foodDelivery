package ru.rbaratov.fooddelivery.orders.context.domain;//package ru.rbaratov.fooddelivery.domain.buyer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import ru.rbaratov.fooddelivery.common.entity.AbstractEntity;

/**
 * Адреса покупателя
 */
@Entity
@Table(name = "buyers_addresses")
public class BuyerAddress extends AbstractEntity {

    /**
     * Улица
     */
    @Column(name = "street")
    private String street;

    /**
     * Здание, дом
     */
    @Column(name = "building")
    private String building;

    /**
     * Квартира
     */
    @Column(name = "apartment")
    private String apartment;

    /**
     * Подъезд
     */
    @Column(name = "entrance")
    private String entrance;

    /**
     * Этаж
     */
    @Column(name = "floor")
    private Long floor;

    /**
     * Комментарий
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Номер дома
     */
    @Column(name = "home_number")
    private String homeNumber;

    /**
     * Широта
     */
    @Column(name = "lat")
    private String lat;

    /**
     * Долгота
     */
    @Column(name = "lng")
    private String lng;

    @OneToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    protected BuyerAddress() {
        //для ORM
    }
}

package ru.rbaratov.fooddelivery.orders.context.entity;

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
public class BuyerAddressEntity extends AbstractEntity {

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
    private BuyerEntity buyer;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public BuyerEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerEntity buyer) {
        this.buyer = buyer;
    }
}

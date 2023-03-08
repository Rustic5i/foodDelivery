//package ru.rbaratov.fooddelivery.domain.user;
//
//import jakarta.persistence.Column;
//import ru.rbaratov.fooddelivery.domain.AbstractEntity;
//
//
///**
// * Адреса
// */
//public class Addresses extends AbstractEntity {
//
//    /**
//     * Адрес
//     */
//    @Column(name = "address")
//    private String address;
//
//    /**
//     * Квартира
//     */
//    @Column(name = "apartment")
//    private String apartment;
//
//    /**
//     * Здание, дом
//     */
//    @Column(name = "building")
//    private String building;
//
//    /**
//     * Комментарий
//     */
//    @Column(name = "comment")
//    private String comment;
//
//    /**
//     * Подъезд
//     */
//    @Column(name = "entrance")
//    private String entrance;
//
//    /**
//     * Этаж
//     */
//    @Column(name = "floor")
//    private Long floor;
//
//    /**
//     * Номер дома
//     */
//    @Column(name = "home_number")
//    private String homeNumber;
//
//    /**
//     * Широта
//     */
//    @Column(name = "lat")
//    private String lat;
//
//    /**
//     * Долгота
//     */
//    @Column(name = "lng")
//    private String lng;
//
//    @Column(name = "user_id")
//    private User user;
//}

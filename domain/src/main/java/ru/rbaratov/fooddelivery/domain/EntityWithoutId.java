//package ru.rbaratov.fooddelivery.domain;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.MappedSuperclass;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//
//import java.util.Date;
//
///**
// * Абстрактная сущность без идентификатора
// */
//@MappedSuperclass
//public abstract class EntityWithoutId {
//
//    /**
//     * Сообщение об ошибке
//     */
//    @Column(name = "error_message")
//    private String errorMessage;
//
//    /**
//     * Код ошибки
//     */
//    @Column(name = "error_code")
//    private String errorCode;
//
//    /**
//     * Дата создания
//     */
//    @Column(name = "created", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date created;
//
//    /**
//     * Дата обновления
//     */
//    @Column(name = "modified")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date modified;
//
//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
//
//    public String getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public Date getCreate() {
//        return created;
//    }
//
//    public void setCreate(Date create) {
//        this.created = create;
//    }
//
//    public Date getModified() {
//        return modified;
//    }
//
//    public void setModified(Date modified) {
//        this.modified = modified;
//    }
//}

package ru.rbaratov.fooddelivery.orders.context.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class BuyerDTO {
    private UUID id;
    private String phone;
    private String name;
    private Collection<String> privileges = new ArrayList<>();
    private Collection<String> principles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<String> privileges) {
        this.privileges = privileges;
    }

    public Collection<String> getPrinciples() {
        return principles;
    }

    public void setPrinciples(Collection<String> principles) {
        this.principles = principles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

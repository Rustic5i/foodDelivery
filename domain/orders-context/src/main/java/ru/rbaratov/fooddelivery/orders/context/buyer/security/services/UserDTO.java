package ru.rbaratov.fooddelivery.orders.context.buyer.security.services;

import java.util.ArrayList;
import java.util.Collection;

public class UserDTO {
    private String phoneNumber;
    private Collection<String> privileges = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<String> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}

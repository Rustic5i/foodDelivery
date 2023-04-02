package ru.rbaratov.fooddelivery.orders.context.buyer.security;

import org.springframework.security.core.GrantedAuthority;

public class Privilege implements GrantedAuthority {

    private String privilege;

    public Privilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String getAuthority() {
        return privilege;
    }
}

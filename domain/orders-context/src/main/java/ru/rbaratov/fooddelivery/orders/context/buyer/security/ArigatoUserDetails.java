package ru.rbaratov.fooddelivery.orders.context.buyer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ArigatoUserDetails implements UserDetails {

    /**
     * Мапа, в которой содержатся привилегии пользователя
     */
    private Collection<Privilege> authorities;
    /**
     * Активен ли пользователь
     */
    private boolean isEnabled;
    /**
     * Пароль
     */
    private String password;
    /**
     * Логин
     */
    private String username;

    public ArigatoUserDetails(Collection<Privilege> authorities, boolean isEnabled, String password, String username) {
        this.authorities = Optional.ofNullable(authorities).orElse(new ArrayList<>());
        this.isEnabled = isEnabled;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

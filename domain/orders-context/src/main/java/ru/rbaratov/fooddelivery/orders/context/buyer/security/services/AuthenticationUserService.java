package ru.rbaratov.fooddelivery.orders.context.buyer.security.services;

import java.util.Optional;

/**
 * Сервис аутентификации пользователя
 */
public interface AuthenticationUserService {

    /**
     * Получить текущего аутентифицированного пользователя
     *
     * @return DTO пользователя
     */
    Optional<UserDTO> getCurrentUser();
}

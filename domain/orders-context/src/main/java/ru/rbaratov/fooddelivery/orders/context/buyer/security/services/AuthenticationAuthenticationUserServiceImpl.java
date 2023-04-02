package ru.rbaratov.fooddelivery.orders.context.buyer.security.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.ArigatoUserDetails;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис аутентификации пользователя
 */
@Service
public class AuthenticationAuthenticationUserServiceImpl implements AuthenticationUserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        if (authentication.getPrincipal() instanceof ArigatoUserDetails) {
            ArigatoUserDetails arigatoUserDetails = (ArigatoUserDetails) authentication.getPrincipal();
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNumber(arigatoUserDetails.getUsername());
            userDTO.setPrivileges(arigatoUserDetails.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.toList()));
            return Optional.of(userDTO);
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhoneNumber(authentication.getName());
            userDTO.setPrivileges(Collections.emptyList());
            return Optional.of(userDTO);
        }
    }
}

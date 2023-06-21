package ru.rbaratov.fooddelivery.orders.context.web.facade;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.AuthenticationUserService;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.services.UserDTO;
import ru.rbaratov.fooddelivery.orders.context.domain.services.CartService;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.dto.CartInfoDTO;
import ru.rbaratov.fooddelivery.orders.context.dto.mapper.CartInfoDTOMapper;
import ru.rbaratov.fooddelivery.orders.context.entity.CartEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.CartEntityRepository;

import java.util.UUID;

@Component
public class CartFacadeServiceImpl implements CartFacadeService {

    @Autowired
    public CartService cartService;
    @Autowired
    private AuthenticationUserService authenticationUserService;
    @Autowired
    private CartEntityRepository cartEntityRepository;
    @Autowired
    private CartInfoDTOMapper cartInfoDTOMapper;

    @Override
    public void addItemToCart(UUID addItem) {
        cartService.addItemToCart(addItem);
    }

    @Override
    public void removeItemFromCart(UUID removeItem) {
        cartService.removeItemFromCart(removeItem);
    }

    @Override
    public CartInfoDTO getBuyerCartInfo() {
        UserDTO currentUser = authenticationUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeApplicationException("Попытка получить информацию по корзине товаров не идентифицированным пользователем"));
        CartEntity cart = cartEntityRepository.findByBuyerPhoneNumber(currentUser.getPhoneNumber()).orElse(null);
        if (cart == null){
            cart = cartService.createCart(new PhoneNumber(currentUser.getPhoneNumber()));
        }
        return cartInfoDTOMapper.toItemDTO(cart);
    }
}

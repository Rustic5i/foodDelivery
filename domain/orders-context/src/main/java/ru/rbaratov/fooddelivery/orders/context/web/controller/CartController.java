package ru.rbaratov.fooddelivery.orders.context.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rbaratov.fooddelivery.common.web.WebURL;
import ru.rbaratov.fooddelivery.orders.context.dto.CartInfoDTO;
import ru.rbaratov.fooddelivery.orders.context.web.facade.CartFacadeService;

import java.util.UUID;

@Controller
@RequestMapping(WebURL.API_PATH_ROOT + "/cart")
public class CartController {

    @Autowired
    public CartFacadeService cartFacadeService;

    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestParam("itemId") UUID itemId) {
        cartFacadeService.addItemToCart(itemId);
        return ResponseEntity.ok("Товар добавлен в корзину");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam("itemId") UUID itemId) {
        cartFacadeService.removeItemFromCart(itemId);
        return ResponseEntity.ok("Товар удален из корзины");
    }

    @GetMapping()
    public ResponseEntity<CartInfoDTO> getCartInfo() {
        CartInfoDTO cartInfoDTO = cartFacadeService.getBuyerCartInfo();
        return ResponseEntity.ok(cartInfoDTO);
    }
}

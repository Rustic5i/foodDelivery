package ru.rbaratov.fooddelivery.services.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rbaratov.fooddelivery.basket.repository.BasketRepository;

import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    {
        System.out.println("dawdawawdawd");
    }

    @Override
    public void addProduct(UUID productId) {
        System.out.println("wdwadwad");
    }

    @Override
    public void deleteProduct(UUID productId) {
        basketRepository.deleteById(productId);
    }
}

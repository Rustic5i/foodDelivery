package ru.rbaratov.fooddelivery.orders.context.domain.repository.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.common.exception.RuntimeApplicationException;
import ru.rbaratov.fooddelivery.common.valueobject.Money;
import ru.rbaratov.fooddelivery.common.valueobject.item.ItemName;
import ru.rbaratov.fooddelivery.orders.context.domain.Item;
import ru.rbaratov.fooddelivery.orders.context.entity.ItemEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.ItemEntityRepository;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private ItemEntityRepository entityRepository;

    @Override
    public Boolean existsByName(String itemName) {
        return entityRepository.existsByName(itemName);
    }

    @Override
    public Item findById(UUID id) {
        Optional<ItemEntity> entityOptional = entityRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new RuntimeApplicationException(MessageFormat.format("Товар с id {} не найден", id));
        }
        ItemEntity item = entityOptional.get();
        return new Item(new ItemName(item.getName()), new Money(item.getPrice()));
    }
}

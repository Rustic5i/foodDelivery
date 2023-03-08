package ru.rbaratov.fooddelivery.menu.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rbaratov.fooddelivery.menu.DTO.ProductDTO;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.ProductCategory;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.CategoryName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.Money;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductDescription;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductSize;
import ru.rbaratov.fooddelivery.menu.repository.category.CategoryRepository;
import ru.rbaratov.fooddelivery.menu.repository.product.ProductRepository;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * Фабрика для создания товара
 */
@Component
public class ProductFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFactory.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Создать новый товар
     *
     * @param productDTO данные о новом товаре
     * @return новый товар
     */
    public Product createNewProduct(ProductDTO productDTO) {
        Objects.requireNonNull(productDTO, "Отсутствуют данные для создания нового товара");
        ProductName productName = new ProductName(productDTO.getProductName());
        Boolean isExistsProduct = productRepository.existsByName(productName);
        if (isExistsProduct) {
            throw new RuntimeException(MessageFormat.format("Товар с именем : {0} уже существует", productName.value()));
        }

        CategoryName categoryName = new CategoryName(productDTO.getCategory());
        Optional<ProductCategory> productCategory = categoryRepository.findByName(categoryName);
        if (productCategory.isEmpty()){
            throw new RuntimeException(
                    MessageFormat.format("Ошибка при создания товара с именем : {0}. Категория товара с именем : {1} еще не создана или не сохранена",
                            productName.value(), categoryName.value()));
        }

        Product newProduct = new Product(productName,
                new Money(productDTO.getPrice()),
                new ProductDescription(productDTO.getDescription()),
                new ProductSize(productDTO.getSize(), productDTO.getSizeUnit()),
                productCategory.get());
        LOGGER.info("Создан новый товар с именем : {}", productName.value());
        return newProduct;
    }
}

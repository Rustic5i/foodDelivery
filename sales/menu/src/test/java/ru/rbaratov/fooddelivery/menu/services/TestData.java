package ru.rbaratov.fooddelivery.menu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import ru.rbaratov.fooddelivery.menu.DTO.ProductDTO;
import ru.rbaratov.fooddelivery.menu.domain.Menu;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.ProductCategory;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.CategoryName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.MenuName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.SizeUnit;
import ru.rbaratov.fooddelivery.menu.factory.MenuFactory;
import ru.rbaratov.fooddelivery.menu.factory.ProductFactory;
import ru.rbaratov.fooddelivery.menu.repository.category.CategoryRepository;
import ru.rbaratov.fooddelivery.menu.repository.menu.MenuRepository;
import ru.rbaratov.fooddelivery.menu.repository.product.ProductRepository;

import java.util.Optional;

/**
 * Подготовка тестовых данных в базе данных
 */
public abstract class TestData {

    @Autowired
    protected MenuRepository menuRepository;
    @Autowired
    protected MenuFactory menuFactory;
    @Autowired
    protected ProductFactory productFactory;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected CategoryRepository categoryRepository;

    protected Product createAndSaveProduct(@NonNull final String productName, @NonNull final String categoryName) {
        ProductCategory productCategory = createAndSaveProductCategory(categoryName);
        ProductDTO productDTO = getProductDTO(productName, productCategory.nameCategory());
        Product product = productFactory.createNewProduct(productDTO);
        return productRepository.save(product);
    }

    protected ProductCategory createAndSaveProductCategory(final String categoryName) {
        CategoryName newCategoryName = new CategoryName(categoryName);
        Optional<ProductCategory> productCategory = categoryRepository.findByName(newCategoryName);
        if (productCategory.isEmpty()){
            return categoryRepository.save(new ProductCategory(newCategoryName));
        }
        return productCategory.get();
    }

    protected Menu createAndSaveMenu(final String menuName) {
        Menu menu = menuFactory.createNewMenu(new MenuName(menuName));
        return menuRepository.save(menu);
    }

    private ProductDTO getProductDTO(@NonNull final String productName, @NonNull CategoryName categoryName) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setCategory(categoryName.value());
        productDTO.setPrice(450f);
        productDTO.setSize(1);
        productDTO.setSizeUnit(SizeUnit.PCS);
        productDTO.setDescription("Очень вкусная пицца из 4 разных сыров");

        return productDTO;
    }
}

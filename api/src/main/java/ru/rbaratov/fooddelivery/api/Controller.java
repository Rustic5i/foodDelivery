package ru.rbaratov.fooddelivery.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rbaratov.fooddelivery.menu.domain.Product;
import ru.rbaratov.fooddelivery.menu.domain.ProductCategory;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.CategoryName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.Money;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductDescription;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductName;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.ProductSize;
import ru.rbaratov.fooddelivery.menu.domain.valueobject.SizeUnit;
import ru.rbaratov.fooddelivery.menu.repository.category.CategoryRepository;
import ru.rbaratov.fooddelivery.menu.repository.product.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/menu")
    public String menu() {
        ProductCategory category = new ProductCategory(new CategoryName("Пиццы"));
        categoryRepository.save(category);
        Product product = new Product(
                new ProductName("wadwa"),
                new Money(12f),
                new ProductDescription("dawd"),
                new ProductSize(10, SizeUnit.KG),
                category);

        repository.save(product);
        return "menu";
    }

    @GetMapping(value = "/base")
    public String delete() {
        List<Product> products = repository.findAll();
        System.out.println(products);
        return "base";
    }
}

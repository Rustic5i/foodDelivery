package ru.rbaratov.fooddelivery.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

//    @Autowired
//    private ProductRepository repository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @GetMapping(value = "/menu")
//    public String menu() {
//        ProductCategory category = new ProductCategory(new CategoryName("Пиццы"));
//        categoryRepository.save(category);
//        Product product = new Product(
//                new ProductName("wadwa"),
//                new Money(12f),
//                new ProductDescription("dawd"),
//                new ProductSize(10, SizeUnit.KG),
//                category);
//
//        repository.save(product);
//        return "menu";
//    }
//
//    @GetMapping(value = "/base")
//    public String delete() {
//        List<Product> products = repository.findAll();
//        System.out.println(products);
//        return "base";
//    }
}

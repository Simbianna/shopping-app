package ru.simbial.shoppingapp.repository;

import ru.simbial.shoppingapp.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Component
public class ProductRepositoryMock {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    
    public List<Product> findAll() {
        return products;
    }

    
    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    
    public void save(Product product) {
        if (product.getId() == null) {
            products.stream()
                    .mapToLong(Product::getId)
                    .max()
                    .ifPresent(i -> product.setId(i + 1));
            if (product.getId() == null) product.setId(1L);
            products.add(product);
        } else {
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId().equals(product.getId())) {
                    p.setPrice(product.getPrice());
                    p.setTitle(product.getTitle());
                    break;
                }
            }
        }
    }

    
    public void deleteById(Long id) {
        products.remove(findById(id));
    }

    
    public List<Product> findFiltered(String input, String minPrice, String maxPrice) {
        Stream<Product> stream = products.stream();
        if (input != null && !input.isEmpty()) {
            stream = stream.filter(p -> p.getTitle().toLowerCase().contains(input.toLowerCase()));
        }
        if (minPrice != null && !minPrice.isEmpty()) {
            stream = stream.filter(p -> p.getPrice() >= Integer.parseInt(minPrice));
        }
        if (maxPrice != null && !maxPrice.isEmpty()) {
            stream = stream.filter(p -> p.getPrice() <= Integer.parseInt(maxPrice));
        }
        return stream.collect(Collectors.toList());
    }
}

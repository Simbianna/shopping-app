package ru.simbial.shoppingapp.repository;

import ru.simbial.shoppingapp.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findByTitle(String title);

    Product findById(Long id);

    void deleteById(Long id);

    void save(Product product);

    List<Product> findFiltered(String input, String minPrice, String maxPrice);

}

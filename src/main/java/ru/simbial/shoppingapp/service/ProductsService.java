package ru.simbial.shoppingapp.service;

import ru.simbial.shoppingapp.entity.Product;
import ru.simbial.shoppingapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllFiltered(String input, String minPrice, String maxPrice) {
        return productRepository.findFiltered(input, minPrice, maxPrice);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

}

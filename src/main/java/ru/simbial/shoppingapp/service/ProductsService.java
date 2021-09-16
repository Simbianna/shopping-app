package ru.simbial.shoppingapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
        return productRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> getAllFilteredWithPaginating(int pageNumber, int pageSize, Specification<Product> productSpec) {
        return productRepository.findAll(productSpec, PageRequest.of(pageNumber, pageSize));
    }

    public List<Product> getAllProds(){
        return (List<Product>) productRepository.findAll();
    }
}

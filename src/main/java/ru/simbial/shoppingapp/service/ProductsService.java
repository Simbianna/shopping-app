package ru.simbial.shoppingapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.simbial.shoppingapp.entity.Product;
import ru.simbial.shoppingapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbial.shoppingapp.repository.specification.ProductSpec;

import java.math.BigDecimal;
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

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    public Page<Product> getAllFilteredWithPaginating(int pageNumber, int pageSize, String input, BigDecimal maxPrice, BigDecimal minPrice, StringBuilder filter) {

        Specification<Product> specification = Specification.where(null);
        if (input != null && input.length() > 0) {
            specification = specification.and(ProductSpec.titleContains(input));
            filter.append("Наименование содержит \"").append(input).append("\". ");
        }
        if (minPrice != null) {
            specification = specification.and(ProductSpec.priceGraterThanOrEquals(minPrice));
            filter.append("Минимальная цена = ").append(minPrice).append(". ");
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpec.priceLessThanOrEquals(maxPrice));
            filter.append("Максимальная цена = ").append(maxPrice).append(". ");
        }

        return productRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    public List<Product> getAllProds() {
        return productRepository.findAll();
    }
}

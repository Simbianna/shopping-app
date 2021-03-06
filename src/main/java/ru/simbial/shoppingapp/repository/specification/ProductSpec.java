package ru.simbial.shoppingapp.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.simbial.shoppingapp.entity.Product;

import java.math.BigDecimal;

public class ProductSpec {

    public static Specification<Product> titleContains(String input) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + input + "%");
    }

    public static Specification<Product> priceLessThanOrEquals(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Product> priceGraterThanOrEquals(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }
}

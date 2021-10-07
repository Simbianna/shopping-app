package ru.simbial.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.simbial.shoppingapp.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query(value = "update products set views = views+1 where id =:id", nativeQuery = true)
    void incrementViewsForProduct(Long id);

    @Modifying
    @Query(value = "update products set views = 0 where id =:id", nativeQuery = true)
    void eraseViewsForProduct(Long id);

    @Modifying
    @Query(value = "update products set views = 0", nativeQuery = true)
    void eraseAllViews(Long id);
}

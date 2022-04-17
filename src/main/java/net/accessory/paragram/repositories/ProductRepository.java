package net.accessory.paragram.repositories;

import net.accessory.paragram.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product getProductByName(@Param("name") String name);

    @Query("SELECT p FROM Product p Where p.id = :id")
    Product getProductById(@Param("id") Long id);
}

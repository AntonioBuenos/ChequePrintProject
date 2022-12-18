package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductDBRepository extends
        CrudRepository<Product, Long>,
        JpaRepository<Product, Long> {
}

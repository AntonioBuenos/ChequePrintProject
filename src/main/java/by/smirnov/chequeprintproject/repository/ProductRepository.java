package by.smirnov.chequeprintproject.repository;

import by.smirnov.chequeprintproject.domain.Product;

public interface ProductRepository {

    Product findById(Long id);
}

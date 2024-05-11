package com.asandovalc1.app.models.repository;

import com.asandovalc1.app.models.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}

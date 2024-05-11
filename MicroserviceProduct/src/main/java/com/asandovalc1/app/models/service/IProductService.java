package com.asandovalc1.app.models.service;

import com.asandovalc1.app.models.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);
    public Product edit(Product product);
    public Boolean deleteById(Long id);
    public boolean exist(Long id);
}

package com.asandovalc1.app.models.service;

import com.asandovalc1.app.models.entity.Product;
import com.asandovalc1.app.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product edit(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exist(Long id){
        try{
            if(id != null) {
                return this.productRepository.existsById(id);
            }
            return false;
        }
        catch (Exception e){
            return false;
        }

    }

}

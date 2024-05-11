package com.asandovalc1.MicroserviceItem.models.service;

import com.asandovalc1.MicroserviceItem.models.Item;
import com.asandovalc1.MicroserviceItem.models.Product;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);

    public Product save(Product product);
    public Product update(Product product);
    public Boolean delete(Long id);

}

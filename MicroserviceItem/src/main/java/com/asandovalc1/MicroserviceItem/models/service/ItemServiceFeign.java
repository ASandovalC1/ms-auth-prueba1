package com.asandovalc1.MicroserviceItem.models.service;

import com.asandovalc1.MicroserviceItem.clientes.ProductClientRest;
import com.asandovalc1.MicroserviceItem.models.Item;
import com.asandovalc1.MicroserviceItem.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductClientRest productClientRest;

    public ItemServiceFeign(ProductClientRest productClientRest) {
        this.productClientRest = productClientRest;
    }

    @Override
    public List<Item> findAll() {
        List<Product> products = this.productClientRest.getAllProducts().getBody();
        List<Item> items = products.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
        System.out.println(items.toString());
        return items;
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Product product = this.productClientRest.getProductById(id).getBody();
        System.out.println(product);
        return new Item(product,quantity);
    }

    @Override
    public Product save(Product product) {
        return this.productClientRest.save(product).getBody();
    }

    @Override
    public Product update(Product product) {
        return this.productClientRest.edit(product).getBody();
    }

    @Override
    public Boolean delete(Long id) {
        return this.productClientRest.delete(id).getBody();
    }
}

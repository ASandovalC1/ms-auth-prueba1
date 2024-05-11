package com.asandovalc1.MicroserviceItem.models.service;

import com.asandovalc1.MicroserviceItem.models.Item;
import com.asandovalc1.MicroserviceItem.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@Primary
public class ItemServiceImpl implements ItemService{
    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(Objects.requireNonNull(clienteRest.getForObject("http://localhost:9092/micro-product/api/products", Product[].class)));
        return products.stream().map(p->new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String,String> pathVariables = new HashMap<String,String>();
        pathVariables.put("id",id.toString());
        Product product = clienteRest.getForObject("http://localhost:9092/micro-product/api/products/{id}", Product.class,pathVariables);
        return new Item(product,quantity);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response =  clienteRest.exchange("http://localhost:9092/micro-product/api/products/create", HttpMethod.POST, body, Product.class);
        Product productResponse = response.getBody();
        return productResponse;
    }

    @Override
    public Product update(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response =  clienteRest.exchange("http://localhost:9092/micro-product/api/products/edit", HttpMethod.PUT, body, Product.class);
        Product productResponse = response.getBody();
        return productResponse;
    }

    @Override
    public Boolean delete(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> requestEntity = new HttpEntity<>(id,headers);

        ResponseEntity<Boolean> response = clienteRest.exchange("http://localhost:9092/micro-product/api/products/delete/{id}",HttpMethod.DELETE,requestEntity,Boolean.class);
        return response.getBody();
    }
}

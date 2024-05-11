package com.asandovalc1.MicroserviceItem.clientes;

import com.asandovalc1.MicroserviceItem.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "micro-product",url = "localhost:8083/api/products") //sin eureka
@FeignClient(name = "micro-product") //con eureka
public interface ProductClientRest {

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>>getAllProducts();

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id);

    @PostMapping("/api/products/create")
    public ResponseEntity<Product> save(@RequestBody Product product);

    @PutMapping("/api/products/edit")
    public ResponseEntity<Product> edit(@RequestBody Product product);

    @DeleteMapping("/api/products/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id);

}
